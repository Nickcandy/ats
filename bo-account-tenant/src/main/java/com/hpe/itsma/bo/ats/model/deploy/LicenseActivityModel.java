package com.hpe.itsma.bo.ats.model.deploy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hpe.itsma.bo.common.api.BaseModelPremium;

import java.util.Objects;

/**
 * Created by wxiaodon on 10/4/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseActivityModel extends BaseModelPremium {
    @JsonProperty("activityType")
    private Integer activityType;

    @JsonProperty("licensePoolLineId")
    private Long licensePoolLineId;

    @JsonProperty("activityTS")
    private Long activityTS;

    @JsonProperty("tenantId")
    private Long tenantId;

    @JsonProperty("capacity")
    private Integer capacity;

    @JsonProperty("controlType")
    private Integer controlType;

    @JsonProperty("licensePoolId")
    private Long licensePoolId;

    @JsonProperty("accessType")
    private Integer accessType;

    @JsonProperty("edition")
    private Integer edition;

    @JsonProperty("productNumber")
    private String productNumber;

    @JsonProperty("mode")
    private Integer mode;

    @JsonProperty("startDate")
    private Long startDate;

    @JsonProperty("endDate")
    private Long endDate;

    @Override
    public String toString() {
        return "LicenseActivity{" +
                "activityType=" + activityType +
                ", licensePoolLineId=" + licensePoolLineId +
                ", activityTS=" + activityTS +
                ", tenantId=" + tenantId +
                ", capacity=" + capacity +
                ", controlType=" + controlType +
                ", licensePoolId=" + licensePoolId +
                ", accessType=" + accessType +
                ", edition=" + edition +
                ", productNumber='" + productNumber + '\'' +
                ", mode=" + mode +
                '}';
    }

    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    public Long getLicensePoolLineId() {
        return licensePoolLineId;
    }

    public void setLicensePoolLineId(Long licensePoolLineId) {
        this.licensePoolLineId = licensePoolLineId;
    }

    public Long getActivityTS() {
        return activityTS;
    }

    public void setActivityTS(Long activityTS) {
        this.activityTS = activityTS;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getControlType() {
        return controlType;
    }

    public void setControlType(Integer controlType) {
        this.controlType = controlType;
    }

    public Long getLicensePoolId() {
        return licensePoolId;
    }

    public void setLicensePoolId(Long licensePoolId) {
        this.licensePoolId = licensePoolId;
    }

    public Integer getAccessType() {
        return accessType;
    }

    public void setAccessType(Integer accessType) {
        this.accessType = accessType;
    }

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LicenseActivityModel that = (LicenseActivityModel) o;
        return Objects.equals(activityType, that.activityType) &&
                Objects.equals(licensePoolLineId, that.licensePoolLineId) &&
                Objects.equals(activityTS, that.activityTS) &&
                Objects.equals(tenantId, that.tenantId) &&
                Objects.equals(capacity, that.capacity) &&
                Objects.equals(controlType, that.controlType) &&
                Objects.equals(licensePoolId, that.licensePoolId) &&
                Objects.equals(accessType, that.accessType) &&
                Objects.equals(edition, that.edition) &&
                Objects.equals(productNumber, that.productNumber) &&
                Objects.equals(mode, that.mode) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityType, licensePoolLineId, activityTS, tenantId, capacity, controlType, licensePoolId, accessType, edition, productNumber, mode, startDate, endDate);
    }
}
