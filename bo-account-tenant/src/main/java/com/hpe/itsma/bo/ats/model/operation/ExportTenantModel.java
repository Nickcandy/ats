package com.hpe.itsma.bo.ats.model.operation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wxiaodon on 9/25/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExportTenantModel {
    @JsonProperty
    private Long tenantId;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public ExportTenantModel(Long tenantId) {
        this.tenantId = tenantId;
    }

    public ExportTenantModel() {
    }
}
