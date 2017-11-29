package com.hpe.itsma.bo.ats.model.deploy;


import java.util.List;

/**
 * Created by wxiaodon on 6/29/2017.
 */
public class RequestTenant {
    private String tenantId;

    private String tenantName;

    private String tenantType;

    private String customerId;

    private String customerUser;

    private Boolean isActive = true;

    private IntegrationUsers integrationUsers;

    private List<LicenseDetail> licenseDetails;

    public List<LicenseDetail> getLicenseDetails() {
        return licenseDetails;
    }

    public void setLicenseDetails(List<LicenseDetail> licenseDetails) {
        this.licenseDetails = licenseDetails;
    }

    private List<ExtendedInfo> extendedInfo;

    private CallbackInformation callbackInformation;

    public RequestTenant(String tenantId, String tenantName, String tenantType, String customerId, String customerUser, Boolean isActive, IntegrationUsers integrationUsers, List<LicenseDetail> licenseDetailLists, List<ExtendedInfo> extendedInfo, CallbackInformation callbackInformation) {
        this.tenantId = tenantId;
        this.tenantName = tenantName;
        this.tenantType = tenantType;
        this.customerId = customerId;
        this.customerUser = customerUser;
        this.isActive = isActive;
        this.integrationUsers = integrationUsers;
        this.licenseDetails = licenseDetailLists;
        this.extendedInfo = extendedInfo;
        this.callbackInformation = callbackInformation;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerUser() {
        return customerUser;
    }

    public void setCustomerUser(String customerUser) {
        this.customerUser = customerUser;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public IntegrationUsers getIntegrationUsers() {
        return integrationUsers;
    }

    public void setIntegrationUsers(IntegrationUsers integrationUsers) {
        this.integrationUsers = integrationUsers;
    }

    public List<ExtendedInfo> getExtendedInfo() {
        return extendedInfo;
    }

    public void setExtendedInfo(List<ExtendedInfo> extendedInfo) {
        this.extendedInfo = extendedInfo;
    }

    public CallbackInformation getCallbackInformation() {
        return callbackInformation;
    }

    public void setCallbackInformation(CallbackInformation callbackInformation) {
        this.callbackInformation = callbackInformation;
    }

    public String getTenantType() {
        return tenantType;
    }

    public void setTenantType(String tenantType) {
        this.tenantType = tenantType;
    }

    public RequestTenant(){}

    @Override
    public String toString() {
        return "RequestTenant{" +
                "tenantId='" + tenantId + '\'' +
                ", tenantName='" + tenantName + '\'' +
                ", tenantType='" + tenantType + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerUser='" + customerUser + '\'' +
                ", isActive=" + isActive +
                ", integrationUsers=" + integrationUsers +
                ", licenseDetails=" + licenseDetails +
                ", extendedInfo=" + extendedInfo +
                ", callbackInformation=" + callbackInformation +
                '}';
    }
}
