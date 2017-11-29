package com.hpe.itsma.bo.ats.model.deploy;

/**
 * Created by wxiaodon on 8/24/2017.
 */
public class LicenseObject {
    private String orderId;

    private String apOrderId;

    private String autopassProductCode;

    private String autopassProductVersion;

    private String subscriptionId;

    private String productNumber;

    private Long autopassFeatureId;

    private String autopassFeatureVersion;

    private String autopassFeatureDescription;

    private String tenantId;

    private Long startDate;

    private Long expirationDate;

    private String ipRange;

    private int capacity;

    private String deviceId;

    private String macAddress;

    private String hostId;

    private String cloudId;

    private long createdTime;

    private String licenseId;

    private String licenseType;

    private String licenseModel;

    private String autopassFeatureType;

    private String createdBy;

    private String notes;

    public LicenseObject(String orderId, String apOrderId, String autopassProductCode, String autopassProductVersion, String subscriptionId, String productNumber, Long autopassFeatureId, String autopassFeatureVersion, String autopassFeatureDescription, String tenantId, Long startDate, Long expirationDate, String ipRange, int capacity, String deviceId, String macAddress, String hostId, String cloudId, long createdTime, String licenseId, String licenseType, String licenseModel, String autopassFeatureType, String createdBy, String notes) {
        this.orderId = orderId;
        this.apOrderId = apOrderId;
        this.autopassProductCode = autopassProductCode;
        this.autopassProductVersion = autopassProductVersion;
        this.subscriptionId = subscriptionId;
        this.productNumber = productNumber;
        this.autopassFeatureId = autopassFeatureId;
        this.autopassFeatureVersion = autopassFeatureVersion;
        this.autopassFeatureDescription = autopassFeatureDescription;
        this.tenantId = tenantId;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
        this.ipRange = ipRange;
        this.capacity = capacity;
        this.deviceId = deviceId;
        this.macAddress = macAddress;
        this.hostId = hostId;
        this.cloudId = cloudId;
        this.createdTime = createdTime;
        this.licenseId = licenseId;
        this.licenseType = licenseType;
        this.licenseModel = licenseModel;
        this.autopassFeatureType = autopassFeatureType;
        this.createdBy = createdBy;
        this.notes = notes;
    }

    public LicenseObject() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getApOrderId() {
        return apOrderId;
    }

    public void setApOrderId(String apOrderId) {
        this.apOrderId = apOrderId;
    }

    public String getAutopassProductCode() {
        return autopassProductCode;
    }

    public void setAutopassProductCode(String autopassProductCode) {
        this.autopassProductCode = autopassProductCode;
    }

    public String getAutopassProductVersion() {
        return autopassProductVersion;
    }

    public void setAutopassProductVersion(String autopassProductVersion) {
        this.autopassProductVersion = autopassProductVersion;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public Long getAutopassFeatureId() {
        return autopassFeatureId;
    }

    public void setAutopassFeatureId(Long autopassFeatureId) {
        this.autopassFeatureId = autopassFeatureId;
    }

    public String getAutopassFeatureVersion() {
        return autopassFeatureVersion;
    }

    public void setAutopassFeatureVersion(String autopassFeatureVersion) {
        this.autopassFeatureVersion = autopassFeatureVersion;
    }

    public String getAutopassFeatureDescription() {
        return autopassFeatureDescription;
    }

    public void setAutopassFeatureDescription(String autopassFeatureDescription) {
        this.autopassFeatureDescription = autopassFeatureDescription;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Long expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getIpRange() {
        return ipRange;
    }

    public void setIpRange(String ipRange) {
        this.ipRange = ipRange;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getCloudId() {
        return cloudId;
    }

    public void setCloudId(String cloudId) {
        this.cloudId = cloudId;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public String getLicenseModel() {
        return licenseModel;
    }

    public void setLicenseModel(String licenseModel) {
        this.licenseModel = licenseModel;
    }

    public String getAutopassFeatureType() {
        return autopassFeatureType;
    }

    public void setAutopassFeatureType(String autopassFeatureType) {
        this.autopassFeatureType = autopassFeatureType;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
