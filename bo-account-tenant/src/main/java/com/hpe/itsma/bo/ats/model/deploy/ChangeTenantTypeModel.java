package com.hpe.itsma.bo.ats.model.deploy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hpe.itsma.bo.ats.service.domain.enumeration.TenantType;

import javax.validation.constraints.NotNull;

/**
 * Created by wxiaodon on 8/25/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangeTenantTypeModel {

    @JsonProperty
    @NotNull
    private TenantType tenantType;

    public TenantType getTenantType() {
        return tenantType;
    }

    public void setTenantType(TenantType tenantType) {
        this.tenantType = tenantType;
    }

    public ChangeTenantTypeModel() {
    }

    public ChangeTenantTypeModel(TenantType tenantType) {
        this.tenantType = tenantType;
    }

    @Override
    public String toString() {
        return "ChangeTenantTypeModel{" +
                "tenantType=" + tenantType +
                '}';
    }
}
