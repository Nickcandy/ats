package com.hpe.itsma.bo.ats.model.deploy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by wxiaodon on 8/25/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeployTenantModel {

    @JsonProperty
    @NotNull
    private Long tenantId = null;

    @JsonProperty
    @NotNull
    private Long accountId = null;

    @JsonProperty
    @NotNull
    private Long adminUserId = null;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Long adminUserId) {
        this.adminUserId = adminUserId;
    }

    public DeployTenantModel() {
    }

    public DeployTenantModel(Long tenantId, Long accountId, Long adminUserId) {
        this.tenantId = tenantId;
        this.accountId = accountId;
        this.adminUserId = adminUserId;
    }
}
