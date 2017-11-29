package com.hpe.itsma.bo.ats.model.deploy;

/**
 * Created by wxiaodon on 6/29/2017.
 */
public class LicenseDetail {

    private String licenseKey;

    private LicenseObject licenseObject;

    public LicenseDetail(String licenseKey, LicenseObject licenseObject) {
        this.licenseKey = licenseKey;
        this.licenseObject = licenseObject;
    }

    public String getLicenseKey() {
        return licenseKey;
    }

    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }

    public LicenseObject getLicenseObject() {
        return licenseObject;
    }

    public void setLicenseObject(LicenseObject licenseObject) {
        this.licenseObject = licenseObject;
    }

    @Override
    public String toString() {
        return "LicenseDetail{" +
                "licenseKey='" + licenseKey + '\'' +
                ", licenseObject=" + licenseObject +
                '}';
    }
}
