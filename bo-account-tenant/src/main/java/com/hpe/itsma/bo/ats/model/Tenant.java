package com.hpe.itsma.bo.ats.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hpe.itsma.bo.ats.service.domain.enumeration.DeployType;
import com.hpe.itsma.bo.ats.service.domain.enumeration.TenantEnvironment;
import com.hpe.itsma.bo.ats.service.domain.enumeration.TenantState;
import com.hpe.itsma.bo.ats.service.domain.enumeration.TenantType;
import com.hpe.itsma.bo.common.api.BaseModelPremium;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@ApiModel(value="Tenant", description="a pojo representing tenant data")
public class Tenant extends BaseModelPremium {

    @ApiModelProperty
    @NotNull
    protected String name = null;

    @ApiModelProperty
    @JsonProperty
    private TenantType tenantType;

    @ApiModelProperty
    @JsonProperty
    private Long account;

    @ApiModelProperty
    @JsonProperty
    @NotNull
    private TenantState tenantState = null;

    @ApiModelProperty
    @JsonProperty
    private LocalDateTime lastActiveDate;

    @ApiModelProperty
    @JsonProperty
    private TenantEnvironment tenantEnvironment;

    @ApiModelProperty
    @JsonProperty
    private Long productInstance;

    @ApiModelProperty
    @JsonProperty
    private Long suiteInstance;

    @ApiModelProperty
    @JsonProperty
    private Boolean mspTenant;

    @ApiModelProperty
    @JsonProperty
    private Boolean managedTenant;

    @ApiModelProperty
    @JsonProperty
    private Long namespace;

    /*@ApiModelProperty
    @JsonProperty
    private List<Long> accountList;*/

    @ApiModelProperty
    @JsonProperty
    private DeployType deployType;

    @ApiModelProperty
    @JsonProperty
    private List<Long> contactList;

    @ApiModelProperty
    @JsonProperty
    private String url;

    @ApiModelProperty
    @JsonProperty
    private Long serviceOfProduct;

    @ApiModelProperty
    @JsonProperty
    private String licenseControlType;

    @ApiModelProperty
    @JsonProperty
    private List<Long> license;

    @ApiModelProperty
    @JsonProperty
    private Boolean accessControl;

    @JsonProperty("tenantAdmin")
    @ApiModelProperty
    private String tenantAdminId;

    public String getTenantAdminId() {
        return tenantAdminId;
    }

    public void setTenantAdminId(String tenantAdminId) {
        this.tenantAdminId = tenantAdminId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public TenantType getTenantType() {
        return tenantType;
    }

    public void setTenantType(TenantType tenantType) {
        this.tenantType = tenantType;
    }

    public TenantState getTenantState() {
        return tenantState;
    }

    public void setTenantState(TenantState tenantState) {
        this.tenantState = tenantState;
    }

    public LocalDateTime getLastActiveDate() {
        return lastActiveDate;
    }

    public void setLastActiveDate(LocalDateTime lastActiveDate) {
        this.lastActiveDate = lastActiveDate;
    }

    public TenantEnvironment getTenantEnvironment() {
        return tenantEnvironment;
    }

    public void setTenantEnvironment(TenantEnvironment tenantEnvironment) {
        this.tenantEnvironment = tenantEnvironment;
    }

    public Long getProductInstance() {
        return productInstance;
    }

    public void setProductInstance(Long productInstance) {
        this.productInstance = productInstance;
    }

    public Long getSuiteInstance() {
        return suiteInstance;
    }

    public void setSuiteInstance(Long suiteInstance) {
        this.suiteInstance = suiteInstance;
    }

    public Boolean getMspTenant() {
        return mspTenant;
    }

    public void setMspTenant(Boolean mspTenant) {
        this.mspTenant = mspTenant;
    }

    public Boolean getManagedTenant() {
        return managedTenant;
    }

    public void setManagedTenant(Boolean managedTenant) {
        this.managedTenant = managedTenant;
    }

    public Long getNamespace() {
        return namespace;
    }

    public void setNamespace(Long namespace) {
        this.namespace = namespace;
    }


    public DeployType getDeployType() {
        return deployType;
    }

    public void setDeployType(DeployType deployType) {
        this.deployType = deployType;
    }

    public List<Long> getContactList() {
        return contactList;
    }

    public void setContactList(List<Long> contactList) {
        this.contactList = contactList;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getServiceOfProduct() {
        return serviceOfProduct;
    }

    public void setServiceOfProduct(Long serviceOfProduct) {
        this.serviceOfProduct = serviceOfProduct;
    }

    public String getLicenseControlType() {
        return licenseControlType;
    }

    public void setLicenseControlType(String licenseControlType) {
        this.licenseControlType = licenseControlType;
    }

    public List<Long> getLicense() {
        return license;
    }

    public void setLicense(List<Long> license) {
        this.license = license;
    }

    public Boolean getAccessControl() {
        return accessControl;
    }

    public void setAccessControl(Boolean accessControl) {
        this.accessControl = accessControl;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Tenant{" +
                ", tenantType=" + tenantType +
                ", tenantState=" + tenantState +
                ", lastActiveDate=" + lastActiveDate +
                ", tenantEnvironment=" + tenantEnvironment +
                ", productInstance=" + productInstance +
                ", suiteInstance=" + suiteInstance +
                ", mspTenant=" + mspTenant +
                ", isManagedTenant=" +managedTenant +
                ", namespace=" + namespace +
                ", deployType=" + deployType +
                ", contactList=" + contactList +
                ", url='" + url + '\'' +
                ", serviceOfProduct=" + serviceOfProduct +
                ", licenseControlType='" + licenseControlType + '\'' +
                ", license=" + license +
                ", accessControl=" + accessControl +
                ", id=" + id +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", lastUpdatedAt=" + lastUpdatedAt +
                ", ownerId='" + ownerId + '\'' +
                ", code='" + code + '\'' +
                ", isDeleted=" + deleted +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", displayLabel='" + displayLabel + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tenant tenant = (Tenant) o;
        return Objects.equals(name, tenant.name) &&
                tenantType == tenant.tenantType &&
                Objects.equals(account, tenant.account) &&
                tenantState == tenant.tenantState &&
                Objects.equals(lastActiveDate, tenant.lastActiveDate) &&
                tenantEnvironment == tenant.tenantEnvironment &&
                Objects.equals(productInstance, tenant.productInstance) &&
                Objects.equals(suiteInstance, tenant.suiteInstance) &&
                Objects.equals(mspTenant, tenant.mspTenant) &&
                Objects.equals(managedTenant, tenant.managedTenant) &&
                Objects.equals(namespace, tenant.namespace) &&
                deployType == tenant.deployType &&
                Objects.equals(contactList, tenant.contactList) &&
                Objects.equals(url, tenant.url) &&
                Objects.equals(serviceOfProduct, tenant.serviceOfProduct) &&
                Objects.equals(licenseControlType, tenant.licenseControlType) &&
                Objects.equals(license, tenant.license) &&
                Objects.equals(accessControl, tenant.accessControl) &&
                Objects.equals(tenantAdminId, tenant.tenantAdminId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tenantType, account, tenantState, lastActiveDate, tenantEnvironment, productInstance, suiteInstance, mspTenant, managedTenant, namespace, deployType, contactList, url, serviceOfProduct, licenseControlType, license, accessControl, tenantAdminId);
    }
}

