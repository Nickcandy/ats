package com.hpe.itsma.bo.ats.model.operation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by wxiaodon on 9/22/2017.
 */
@ApiModel(value="RequestTenantModel", description="a pojo representing clone or move tenant data")
public class RequestTenantModel {
    @ApiModelProperty
    @NotNull
    private String SourceTenantId = null;

    @ApiModelProperty
    @NotNull
    private String TargetTenantId = null;

    private String[] Strategies;

    public RequestTenantModel(String sourceTenantId, String targetTenantId, String[] strategies) {
        SourceTenantId = sourceTenantId;
        TargetTenantId = targetTenantId;
        Strategies = strategies;
    }

    public RequestTenantModel() {
    }

    public String getSourceTenantId() {
        return SourceTenantId;
    }

    public void setSourceTenantId(String sourceTenantId) {
        SourceTenantId = sourceTenantId;
    }

    public String getTargetTenantId() {
        return TargetTenantId;
    }

    public void setTargetTenantId(String targetTenantId) {
        TargetTenantId = targetTenantId;
    }

    public String[] getStrategies() {
        return Strategies;
    }

    public void setStrategies(String[] strategies) {
        Strategies = strategies;
    }
}
