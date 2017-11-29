package com.hpe.itsma.bo.ats.agent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

/**
 * Created by wxiaodon on 8/24/2017.
 */
@Service
public class SawIdmAgent extends BaseAgent {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Value("${idm.schema}")
    private String idmSchema;

    @Value("${idm.host}")
    private String idmHost;

    @Value("${idm.port:}")
    private String idmPort;

    @Value("${idm.context:}")
    private String context;

    @Value("${idm.auth-uri}")
    private String idmAuthUri;

    @Value("${saw.saas-integration.tenant}")
    private String sawOperatorTenant;

    @Value("${saw.saas-integration.operatorAdminUser}")
    private String sawOperatorAdmin;

    @Value("${saw.saas-integration.operatorAdminUserPassword}")
    private String sawOperatorAdminPaw;

    @Override
    protected RestTemplate restTemplate() {
        return restTemplateBuilder.build();
    }

    @Override
    protected String getSchema() {
        return idmSchema;
    }

    @Override
    public String getHost() {
        return idmHost;
    }

    @Override
    public String getPort() {
        return idmPort;
    }

    @Override
    protected String getContext() {
        return context;
    }

    protected String getIdmToken() {
        String url = MessageFormat.format(idmAuthUri, sawOperatorAdmin, sawOperatorAdminPaw, sawOperatorTenant);
        return this.doGet(url, new ParameterizedTypeReference<String>() {
        });
    }

    @Override
    protected HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
