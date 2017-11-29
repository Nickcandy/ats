package com.hpe.itsma.bo.ats.model.operation;

/**
 * Created by wxiaodon on 10/11/2017.
 */
public class MoveTenantResponse {

    private String cloneProcessId;

    private String sourceTenantId;

    private String targetTenantId;

    public MoveTenantResponse(String cloneProcessId, String sourceTenantId, String targetTenantId) {
        this.cloneProcessId = cloneProcessId;
        this.sourceTenantId = sourceTenantId;
        this.targetTenantId = targetTenantId;
    }

    public MoveTenantResponse() {
    }

    public String getCloneProcessId() {
        return cloneProcessId;
    }

    public void setCloneProcessId(String cloneProcessId) {
        this.cloneProcessId = cloneProcessId;
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

}
