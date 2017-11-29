package com.hpe.itsma.bo.ats.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hpe.itsma.bo.ats.service.domain.enumeration.ProductType;
import com.hpe.itsma.bo.common.api.BaseModelPremium;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Account
 */
@ApiModel(value="Account", description="a pojo representing account data")
public class ProductInstance extends BaseModelPremium {

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

    public ProductInstance() { }

    public ProductInstance(ProductType productType, Long suiteInstance, String urlPublic) {
        this.productType = productType;
        this.suiteInstance = suiteInstance;
        this.urlPublic = urlPublic;
    }

    public ProductInstance id(Long id) {
        this.id = id;
        return this;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProductInstance that = (ProductInstance) o;
        return productType == that.productType &&
                Objects.equals(suiteInstance, that.suiteInstance) &&
                Objects.equals(urlPublic, that.urlPublic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productType, suiteInstance, urlPublic);
    }
}

