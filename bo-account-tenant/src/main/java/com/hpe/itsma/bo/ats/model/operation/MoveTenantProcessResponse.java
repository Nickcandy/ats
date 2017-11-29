package com.hpe.itsma.bo.ats.model.operation;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wxiaodon on 10/12/2017.
 */
public class MoveTenantProcessResponse {
    @JsonProperty(value = "Status")
    private String status;
    @JsonProperty(value = "SourceTenantId")
    private String sourceTenantId;
    @JsonProperty(value = "TargetTenantId")
    private String targetTenantId;
    @JsonProperty(value = "Id")
    private String id;

    public MoveTenantProcessResponse() {
    }

    public MoveTenantProcessResponse(String status, String sourceTenantId, String targetTenantId, String id) {
        this.status = status;
        this.sourceTenantId = sourceTenantId;
        this.targetTenantId = targetTenantId;
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSourceTenantId() {
        return sourceTenantId;
    }

    public void setSourceTenantId(String sourceTenantId) {
        this.sourceTenantId = sourceTenantId;
    }

    public String getTargetTenantId() {
        return targetTenantId;
    }

    public void setTargetTenantId(String targetTenantId) {
        this.targetTenantId = targetTenantId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
