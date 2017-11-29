package com.hpe.itsma.bo.ats.agent;

import com.hpe.itsma.bo.ats.model.deploy.LicenseAssignModel;
import com.hpe.itsma.bo.ats.model.deploy.LicenseActivityModel;
import com.hpe.itsma.bo.common.api.PageQueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

/**
 * Created by wxiaodon on 10/4/2017.
 */
@Service
public class BoLicenseAgent extends BaseBoAgent {

    private static final String LICENSE_ASSIGNMENTS_BY_TENANTID = "/licenseActivities/tenant/{0}";
    private static final String ASSIGN_LICENSE = "/bo/rest/licenseActivities/assign";

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Value("${bo.license.schema}")
    private String licenseSchema;

    @Value("${bo.license.host}")
    private String licenseHost;

    @Value("${bo.license.port}")
    private String licensePort;

    @Value("${bo.license.context:}")
    private String licenseContext;

    @Override
    protected RestTemplate restTemplate() {
        return restTemplateBuilder.build();
    }

    @Override
    protected String getSchema() {
        return licenseSchema;
    }

    @Override
    protected String getHost() {
        return licenseHost;
    }

    @Override
    protected String getPort() {
        return licensePort;
    }

    @Override
    protected String getContext() {
        return licenseContext;
    }

    public PageQueryModel<LicenseActivityModel> getLicensesByTenantId(Long tenantId) {
        String uri = MessageFormat.format(LICENSE_ASSIGNMENTS_BY_TENANTID, String.valueOf(tenantId));
        return doGet(uri, new ParameterizedTypeReference<PageQueryModel<LicenseActivityModel>>() { });
    }

    public Object assignLicense(LicenseAssignModel model) {
        return doPost(ASSIGN_LICENSE, model, new ParameterizedTypeReference<Object>() {
        });
    }
}
