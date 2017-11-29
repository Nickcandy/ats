package com.hpe.itsma.bo.ats.model.deploy;

/**
 * Created by wxiaodon on 6/29/2017.
 */
public class ExtendedInfo {

    private String product;

    private ProductExtendedInfo productExtendedInfo;

    public ExtendedInfo(String product, ProductExtendedInfo productExtendedInfo) {
        this.product = product;
        this.productExtendedInfo = productExtendedInfo;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public ProductExtendedInfo getProductExtendedInfo() {
        return productExtendedInfo;
    }

    public void setProductExtendedInfo(ProductExtendedInfo productExtendedInfo) {
        this.productExtendedInfo = productExtendedInfo;
    }

    @Override
    public String toString() {
        return "ExtendedInfo{" +
                "product='" + product + '\'' +
                ", productExtendedInfo=" + productExtendedInfo +
                '}';
    }
}
