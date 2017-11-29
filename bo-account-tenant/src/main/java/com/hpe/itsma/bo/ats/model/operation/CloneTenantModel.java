package com.hpe.itsma.bo.ats.model.operation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by wxiaodon on 10/13/2017.
 */
public class CloneTenantModel {
    @ApiModelProperty
    @NotNull
    @JsonProperty
    private String sourceTenantId = null;

    @ApiModelProperty
    @NotNull
    @JsonProperty
    private String targetTenantId = null;

    @JsonProperty
    private Boolean importRoleAndPermissions;

    public CloneTenantModel(String sourceTenantId, String targetTenantId, Boolean importRoleAndPermissions) {
        this.sourceTenantId = sourceTenantId;
        this.targetTenantId = targetTenantId;
        this.importRoleAndPermissions = importRoleAndPermissions;
    }

    public CloneTenantModel() {
    }

    public Boolean getImportRoleAndPermissions() {
        return importRoleAndPermissions;
    }

    public void setImportRoleAndPermissions(Boolean importRoleAndPermissions) {
        this.importRoleAndPermissions = importRoleAndPermissions;
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
