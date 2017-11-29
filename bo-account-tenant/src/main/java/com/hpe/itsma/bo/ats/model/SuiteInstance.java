package com.hpe.itsma.bo.ats.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hpe.itsma.bo.ats.service.domain.enumeration.*;
import com.hpe.itsma.bo.common.api.BaseModelPremium;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * Account
 */
@ApiModel(value="Account", description="a pojo representing account data")
public class SuiteInstance extends BaseModelPremium {

    @ApiModelProperty
    @JsonProperty
    @NotNull
    private SuiteType suiteType = null;

    public SuiteType getSuiteType() {
        return suiteType;
    }

    public void setSuiteType(SuiteType suiteType) {
        this.suiteType = suiteType;
    }

    public SuiteInstance() { }

    public SuiteInstance(SuiteType suiteType) {
        this.suiteType = suiteType;
    }

    public SuiteInstance id(Long id) {
        this.id = id;
        return this;
    }
}

