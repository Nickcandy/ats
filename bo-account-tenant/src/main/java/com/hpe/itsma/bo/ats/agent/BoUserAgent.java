package com.hpe.itsma.bo.ats.agent;

import com.hpe.itsma.bo.ats.model.User2TenantRelationModel;
import com.hpe.itsma.bo.common.api.MultipleChangeResult;
import com.hpe.itsma.bo.common.api.PageQueryModel;
import com.hpe.itsma.bo.common.api.UserJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

/**
 * Created by wxiaodon on 8/30/2017.
 */
@Service
public class BoUserAgent extends BaseBoAgent {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Value("${bo.user.schema}")
    private String userSchema;

    @Value("${bo.user.host}")
    private String userHost;

    @Value("${bo.user.port}")
    private String userPort;

    @Value("${bo.user.uri}")
    private String userUri;

    @Value("${bo.user.tenant-user-uri}")
    private String tenantUserRelationUri;

    @Value("${bo.user.account-user-uri}")
    private String userAccountUri;

    @Value("${bo.gateway.context:}")
    private String userContext;

    @Override
    protected RestTemplate restTemplate() {
        return restTemplateBuilder.build();
    }

    @Override
    protected String getSchema() {
        return userSchema;
    }

    @Override
    protected String getHost() {
        return userHost;
    }

    @Override
    protected String getPort() {
        return userPort;
    }

    @Override
    protected String getContext() {
        return userContext;
    }

    public UserJSON getUserById(Long id) {
        String url = MessageFormat.format(userUri, id.toString());
        return this.doGet(url, new ParameterizedTypeReference<UserJSON>() {
        });
    }

    public MultipleChangeResult updateTenantAndUserRelation(User2TenantRelationModel model) {
        return this.doPut(tenantUserRelationUri, model, new ParameterizedTypeReference<MultipleChangeResult>() {
        });
    }

    public PageQueryModel<UserJSON> listUserByAccountId(Long accountId) {
        String url = MessageFormat.format(userAccountUri, accountId.toString(), 20, 0);
        return this.doGet(url, new ParameterizedTypeReference<PageQueryModel<UserJSON>>() {
        });
    }

}
