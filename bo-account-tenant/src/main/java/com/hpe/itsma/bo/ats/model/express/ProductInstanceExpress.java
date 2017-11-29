package com.hpe.itsma.bo.ats.model.express;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hpe.itsma.bo.ats.service.domain.enumeration.ProductType;
import com.hpe.itsma.bo.common.api.BaseModelExpress;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Account
 */
@ApiModel(value = "ProductInstanceExpress", description = "a pojo representing ProductInstanceExpress data for create ProductInstance")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Validated
public class ProductInstanceExpress extends BaseModelExpress {

    @ApiModelProperty
    @JsonProperty
    @NotNull
    private ProductType productType = null;

    @ApiModelProperty
    @JsonProperty
    @NotNull
    private Long suiteInstance = null;

    @ApiModelProperty
    @JsonProperty
    private String urlPublic;

    public ProductInstanceExpress() {
    }

    public ProductInstanceExpress(ProductType productType, Long suiteInstance, String urlPublic) {
        this.productType = productType;
        this.suiteInstance = suiteInstance;
        this.urlPublic = urlPublic;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Long getSuiteInstance() {
        return suiteInstance;
    }

    public void setSuiteInstance(Long suiteInstance) {
        this.suiteInstance = suiteInstance;
    }

    public String getUrlPublic() {
        return urlPublic;
    }

    public void setUrlPublic(String urlPublic) {
        this.urlPublic = urlPublic;
    }

}

