package com.hpe.itsma.bo.ats.agent;

import com.hpe.itsma.bo.ats.model.BoTokenUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wxiaodon on 8/28/2017.
 */
@Service
public class BoGatewayAgent extends BaseBoAgent {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Value("${bo.gateway.schema}")
    private String gatewaySchema;

    @Value("${bo.gateway.host}")
    private String gatewayHost;

    @Value("${bo.gateway.port}")
    private String gatewayPort;

    @Value("${bo.gateway.uri}")
    private String gatewayUri;

    @Value("${bo.gateway.context:}")
    private String gatewayContext;

    @Override
    protected RestTemplate restTemplate() {
        return restTemplateBuilder.build();
    }

    @Override
    protected String getSchema() {
        return gatewaySchema;
    }

    @Override
    protected String getHost() {
        return gatewayHost;
    }

    @Override
    protected String getPort() {
        return gatewayPort;
    }

    @Override
    protected String getContext() {
        return gatewayContext;
    }

    public String getToken(String username, String password){
        BoTokenUser user = new BoTokenUser(username, password);
        return this.doPost(gatewayUri, user, new ParameterizedTypeReference<String>() {
        });
    }
}
