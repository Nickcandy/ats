package com.hpe.itsma.bo.ats.agent;

import com.hpe.itsma.bo.ats.model.operation.RequestTenantModel;
import com.hpe.itsma.bo.ats.model.deploy.DeployTenantResponse;
import com.hpe.itsma.bo.ats.model.operation.CloneTenantProcessResponse;
import com.hpe.itsma.bo.ats.model.operation.CloneTenantResponse;
import com.hpe.itsma.bo.ats.model.operation.MoveTenantProcessResponse;
import com.hpe.itsma.bo.ats.model.operation.MoveTenantResponse;
import com.hpe.itsma.bo.ats.service.domain.enumeration.TenantType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.Collections;

/**
 * Created by wxiaodon on 6/29/2017.
 */
@Service
public class SawTenantManageAgent extends BaseSawAgent {

    private static final String MOVE_TENANT = "/TenantManagement/move";
    private static final String MOVE_TENANT_PROCESS = "/TenantManagement/move/{0}";
    private static final String CHANGE_TENANT_TYPE = "/TenantManagement/tenant/{0}/properties/type/{1}";


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
        return "/rest/" + sawOperatorTenant;
    }

    public DeployTenantResponse deployTenantToSaw(Object body) {
        return this.doPost(sawTenantServiceUri, body, new ParameterizedTypeReference<DeployTenantResponse>() {
        });
    }

    public CloneTenantResponse cloneTenant(RequestTenantModel model) {
        return this.doPost(sawTenantCloneUri, model, new ParameterizedTypeReference<CloneTenantResponse>() {
        });
    }

    public CloneTenantProcessResponse getCloneTenantProcessStatus(String cloneProcessId) {
        String statusUri = MessageFormat.format(sawTenantCloneStatusUri, cloneProcessId);
        return this.doGet(statusUri, new ParameterizedTypeReference<CloneTenantProcessResponse>() {
        });
    }

    public MoveTenantResponse moveTenant(RequestTenantModel model) {
        return this.doPost(MOVE_TENANT, model, new ParameterizedTypeReference<MoveTenantResponse>() {
        });
    }

    public MoveTenantProcessResponse getMoveTenantProcessStatus(String moveProcessId) {
        String statusUri = MessageFormat.format(MOVE_TENANT_PROCESS, moveProcessId);
        return this.doGet(statusUri, new ParameterizedTypeReference<MoveTenantProcessResponse>() {
        });
    }

    public void changeTenantType(Long tenantId, TenantType tenantType) {
        String tenantTypeStr;
        switch (tenantType) {
            case TRIAL:
                tenantTypeStr = "Trial";
                break;
            case INTERNAL:
                tenantTypeStr = "Internal";
                break;
            case PRODUCTION:
                tenantTypeStr = "Production";
                break;
            case TRIALPROVISION:
                tenantTypeStr = "PreProvisioned";
                break;
            default:
                tenantTypeStr = "Dev";
        }
        String statusUri = MessageFormat.format(CHANGE_TENANT_TYPE, tenantId.toString(), tenantTypeStr);
        this.doPut(statusUri, Collections.emptyMap(), new ParameterizedTypeReference<String>() {
        });
    }
}
