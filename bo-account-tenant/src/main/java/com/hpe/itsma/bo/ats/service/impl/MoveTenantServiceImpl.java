package com.hpe.itsma.bo.ats.service.impl;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.hp.run.DefaultConfiguration;
import com.hp.run.ScriptConfiguration;
import com.hp.run.ScriptExecutor;
import com.hp.run.callback.ScriptExecCallback;
import com.hpe.itsma.bo.ats.agent.SawTenantManageAgent;
import com.hpe.itsma.bo.ats.agent.SawUmsAgent;
import com.hpe.itsma.bo.ats.model.operation.ExportTenantModel;
import com.hpe.itsma.bo.ats.model.operation.ImportTenantModel;
import com.hpe.itsma.bo.ats.model.operation.MigrationUserModel;
import com.hpe.itsma.bo.ats.model.operation.RequestTenantModel;
import com.hpe.itsma.bo.ats.service.MoveTenantService;
import com.hpe.itsma.bo.ats.model.operation.MoveTenantProcessResponse;
import com.hpe.itsma.bo.ats.model.operation.MoveTenantResponse;
import com.hpe.itsma.bo.common.exception.BoInvalidParameterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

/**
 * Created by wxiaodon on 10/24/2017.
 */
@Service
public class MoveTenantServiceImpl implements MoveTenantService {

    @Autowired
    private SawTenantManageAgent sawAgent;

    @Autowired
    private SawUmsAgent sawumsAgent;

    private static final String IMPORT_TENANT_FILE_FOLDER_PATH = "/tmp/tenant-import";

    private Logger logger = LoggerFactory.getLogger(MoveTenantServiceImpl.class);

    private static final long TIMER_INTERVAL = 60000;
    private static final int TIME_OUT = 180;
    private static final String RESULT = "Result: ";
    private StringBuilder improveResult = new StringBuilder();
    private StringBuilder exportResult = new StringBuilder();

    @Override
    public Object importTenant(ImportTenantModel model) {
        improveResult = new StringBuilder();
        String importThreadName = "Import-Thread-pool-%d";
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat(importThreadName).build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        singleThreadPool.execute(() -> {
            logger.info("entry import thread");
            try {
                ScriptExecutor.startImport(ScriptConfiguration.ImportStep.Build(
                        new File(IMPORT_TENANT_FILE_FOLDER_PATH + "/" + model.getFilePath()),
                        model.getTenantId().toString(),
                        DefaultConfiguration.getImportPostgresConf(),
                        DefaultConfiguration.getImportIdolConf(),
                        DefaultConfiguration.getImportNetAppConf()
                ), new ScriptExecCallback() {
                    @Override
                    public void onLine(String s) {
                        logger.info("On Line: " + s);
                    }

                    @Override
                    public void onError(Throwable throwable, String s) {
                        logger.error(throwable.getMessage());
                        logger.info("On Error: " + s);
                    }

                    @Override
                    public void onFinished(String s) {
                        improveResult.append(RESULT).append(s);
                        logger.info(RESULT + s);
                        String sourceTenantId;
                        if (model.getFilePath().startsWith("data-")) {
                            sourceTenantId = model.getFilePath().split("-")[1];
                        } else {
                            throw new BoInvalidParameterException(Arrays.asList("operation tenant payload", "tenant file name").toString(), Arrays.asList(String.valueOf(model), String.valueOf(model.getFilePath())).toString());
                        }

                        MoveTenantResponse result = scriptFinishCallMoveTenantApi(model, sourceTenantId);
                        moveTenantDoneCallMigration(model.getTenantId().toString(), result);
                    }

                    @Override
                    public void onStepFinished(String s) {
                        logger.info("On step Finished: " + s);
                    }

                    @Override
                    public void onStepChanged(String s) {
                        logger.info("On step Changed: " + s);
                    }

                    @Override
                    public void onErrorExit(String s) {
                        logger.info("On error Exit: " + s);
                    }
                });
            } catch (FileNotFoundException e) {
                logger.trace(e.getMessage());
            }
        });
        singleThreadPool.shutdown();
        return true;
    }

    private MoveTenantResponse scriptFinishCallMoveTenantApi(ImportTenantModel model, String sourceTenantId) {
        RequestTenantModel requestModel = new RequestTenantModel();
        requestModel.setSourceTenantId(sourceTenantId);
        requestModel.setTargetTenantId(model.getTenantId().toString());
        String[] strategies = {"CopyAuthorizations"};
        if (model.getImportRoleAndPermissions() == null || model.getImportRoleAndPermissions().equals(true)) {
            requestModel.setStrategies(strategies);
        }
        return sawAgent.moveTenant(requestModel);
    }

    private void moveTenantDoneCallMigration(String tenantId, MoveTenantResponse moveTenantResponse) {
        MoveTenantProcessResponse processResponse = sawAgent.getMoveTenantProcessStatus(moveTenantResponse.getCloneProcessId());
        int requestCall = 0;
        while (!"Failed".equals(processResponse.getStatus()) && !"Aborted".equals(processResponse.getStatus()) && !"Success".equals(processResponse.getStatus()) && (requestCall < TIME_OUT)) {
            try {
                Thread.sleep(TIMER_INTERVAL);
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
                Thread.currentThread().interrupt();
            }
            requestCall++;
            processResponse = sawAgent.getMoveTenantProcessStatus(moveTenantResponse.getCloneProcessId());
            logger.info("operation tenant process status: " + processResponse.getStatus());
        }
        logger.info("operation tenant process done. The process status: " + processResponse.getStatus());
        if ("Success".equals(processResponse.getStatus())) {
            MigrationUserModel migrationUserModel = new MigrationUserModel();
            migrationUserModel.setAuthType("DB");
            migrationUserModel.setResetExternalId(true);
            migrationUserModel.setUsers(new ArrayList<>());
            sawumsAgent.migrationUser(migrationUserModel, tenantId);
        }
    }

    @Override
    public Object exportTenant(ExportTenantModel model) {
        exportResult = new StringBuilder();
        String importThreadName = "Export-Thread-pool-%d";
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat(importThreadName).build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        singleThreadPool.execute(() ->
                ScriptExecutor.startExport(ScriptConfiguration.ExportStep.Build(
                        model.getTenantId().toString(),
                        DefaultConfiguration.getExportPostgresConf(),
                        DefaultConfiguration.getExportIdolConf(),
                        DefaultConfiguration.getExportNetAppConf()
                ), new ScriptExecCallback() {
                    @Override
                    public void onLine(String s) {
                        logger.info("On Line : " + s);
                    }

                    @Override
                    public void onError(Throwable throwable, String s) {
                        logger.error(throwable.getMessage());
                        logger.error("On Error : " + s);
                    }

                    @Override
                    public void onFinished(String s) {
                        exportResult.append(RESULT).append("On Finished : " + s);
                        logger.info(s);
                    }

                    @Override
                    public void onStepFinished(String s) {
                        logger.info("On Step Finished: " + s);
                    }

                    @Override
                    public void onStepChanged(String s) {
                        logger.info("On Step Changed: " + s);
                    }

                    @Override
                    public void onErrorExit(String s) {
                        logger.info("On error Exit: " + s);
                    }
                })
        );
        singleThreadPool.shutdown();
        return true;
    }

    @Override
    public List<String> listImportTenant() {
        List<String> result = new ArrayList<>();
        try (Stream<Path> pathStream = Files.walk(Paths.get(IMPORT_TENANT_FILE_FOLDER_PATH))) {
            pathStream
                    .filter(Files::isRegularFile)
                    .forEach(item -> result.add(item.getFileName().toString()));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return result;
    }
}
