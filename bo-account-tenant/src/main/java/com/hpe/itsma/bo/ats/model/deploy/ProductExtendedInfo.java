package com.hpe.itsma.bo.ats.model.deploy;

/**
 * Created by wxiaodon on 8/24/2017.
 */
public class ProductExtendedInfo {
    private String url;

    private String applicationName;

    private String fireAlerts;

    private String region;

    private String timeZone;

    private String environment;

    private String purchaseStatus;

    public ProductExtendedInfo(String url, String applicationName, String fireAlerts, String region, String timeZone, String environment, String purchaseStatus) {
        this.url = url;
        this.applicationName = applicationName;
        this.fireAlerts = fireAlerts;
        this.region = region;
        this.timeZone = timeZone;
        this.environment = environment;
        this.purchaseStatus = purchaseStatus;
    }

    public ProductExtendedInfo() {
    }

    public String getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(String purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getFireAlerts() {
        return fireAlerts;
    }

    public void setFireAlerts(String fireAlerts) {
        this.fireAlerts = fireAlerts;
    }
}
