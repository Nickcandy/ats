package com.hpe.itsma.bo.ats.model.express;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hpe.itsma.bo.ats.service.domain.enumeration.SuiteType;
import com.hpe.itsma.bo.common.api.BaseModelExpress;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Account
 */
@ApiModel(value = "SuiteInstanceExpress", description = "a pojo representing SuiteInstanceExpress data for create SuiteInstance")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Validated
public class SuiteInstanceExpress extends BaseModelExpress {

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

    public SuiteInstanceExpress() { }

    public SuiteInstanceExpress(SuiteType suiteType) {
        this.suiteType = suiteType;
    }
}

