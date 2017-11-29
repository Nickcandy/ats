package com.hpe.itsma.bo.ats.model.deploy;

/**
 * Created by wxiaodon on 10/4/2017.
 */
public class LicenseAssignModel {
    private Long tenantId;

    private Long licensePoolLineId;

    private Integer capacity;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getLicensePoolLineId() {
        return licensePoolLineId;
    }

    public void setLicensePoolLineId(Long licensePoolLineId) {
        this.licensePoolLineId = licensePoolLineId;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
