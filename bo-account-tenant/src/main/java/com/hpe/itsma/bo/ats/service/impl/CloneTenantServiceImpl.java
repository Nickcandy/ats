package com.hpe.itsma.bo.ats.service.impl;

import com.hpe.itsma.bo.ats.agent.SawTenantManageAgent;
import com.hpe.itsma.bo.ats.model.operation.CloneTenantModel;
import com.hpe.itsma.bo.ats.model.operation.RequestTenantModel;
import com.hpe.itsma.bo.ats.service.CloneTenantService;
import com.hpe.itsma.bo.ats.model.operation.CloneTenantProcessResponse;
import com.hpe.itsma.bo.ats.model.operation.CloneTenantResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wxiaodon on 10/24/2017.
 */
@Service
public class CloneTenantServiceImpl implements CloneTenantService {

    @Autowired
    private SawTenantManageAgent sawAgent;

    private Logger logger = LoggerFactory.getLogger(CloneTenantServiceImpl.class);

    @Override
    public CloneTenantResponse cloneTenant(CloneTenantModel model) {
        RequestTenantModel requestTenantModel = new RequestTenantModel();
        requestTenantModel.setTargetTenantId(model.getTargetTenantId());
        requestTenantModel.setSourceTenantId(model.getSourceTenantId());
        String[] strategies = {"CopyAuthorizations"};
        if (model.getImportRoleAndPermissions() == null || model.getImportRoleAndPermissions().equals(true)) {
            requestTenantModel.setStrategies(strategies);
        }
        CloneTenantResponse response = sawAgent.cloneTenant(requestTenantModel);
        logger.info("clone tenant response process id: {}", response.getCloneProcessId());
        return response;
    }

    @Override
    public CloneTenantProcessResponse getCloneTenantProcessStatus(String processId) {
        CloneTenantProcessResponse result = sawAgent.getCloneTenantProcessStatus(processId);
        logger.info("clone tenant process {} status: {}", processId, result.getStatus());
        return result;
    }
}
