package com.hpe.itsma.bo.ats.agent;

import com.hpe.itsma.bo.ats.model.operation.MigrationUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

/**
 * Created by wxiaodon on 10/12/2017.
 */
@Service
public class SawUmsAgent extends BaseSawAgent {

    private static final String MIGRATION_USER = "/{0}/ums/migrateUsers";

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Value("${saw.schema}")
    private String sawSchema;

    @Value("${saw.host}")
    private String sawHost;

    @Value("${saw.port}")
    private String sawPort;

    @Value("${saw.tenant.service.uri}")
    private String sawTenantServiceUri;

    @Value("${saw.tenant.clone.uri}")
    private String sawTenantCloneUri;

    @Value("${saw.tenant.clone.processStatusUri}")
    private String sawTenantCloneStatusUri;

    @Value("${saw.saas-integration.operatorTenant}")
    private String sawOperatorTenant;

    @Value("${saw.callback.service.host}")
    private String sawCallbackHost;

    @Value("${saw.callback.service.port}")
    private String sawCallbackPost;

    @Value("${saw.callback.service.url}")
    private String sawCallbackUrl;

    @Override
    protected RestTemplate restTemplate() {
        return restTemplateBuilder.build();
    }

    @Override
    protected String getSchema() {
        return sawSchema;
    }

    @Override
    protected String getHost() {
        return sawHost;
    }

    @Override
    protected String getPort() {
        return sawPort;
    }

    @Override
    protected String getContext() {
        return "/rest";
    }

    public Object migrationUser(MigrationUserModel model, String tenantId) {
        String migrationUri = MessageFormat.format(MIGRATION_USER, tenantId);
        return this.doPost(migrationUri, model, new ParameterizedTypeReference<Object>() {
        });
    }
}
