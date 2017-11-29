package com.hpe.itsma.bo.ats.service.domain;

import com.hpe.itsma.bo.ats.service.domain.enumeration.ProductType;
import com.hpe.itsma.bo.common.persistence.BaseEntity;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product_instance_entity")
@Audited // to save audit db log into tenant_entity_aud
public class ProductInstanceEntity extends BaseEntity{

    public static final String ENTITY_TYPE = "ProductInstance";

    @Enumerated(EnumType.STRING)
    @Column(name = "product_type")
    private ProductType productType;

    @Column(name = "suite_instance_id")
    private Long suiteInstance;

    @Column(name = "url_public")
    private String urlPublic;

    public static String getEntityType() {
        return ENTITY_TYPE;
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

    public ProductInstanceEntity(ProductType productType, Long suiteInstance, String urlPublic) {
        this.productType = productType;
        this.suiteInstance = suiteInstance;
        this.urlPublic = urlPublic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProductInstanceEntity that = (ProductInstanceEntity) o;
        return productType == that.productType &&
                Objects.equals(suiteInstance, that.suiteInstance) &&
                Objects.equals(urlPublic, that.urlPublic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productType, suiteInstance, urlPublic);
    }
}
