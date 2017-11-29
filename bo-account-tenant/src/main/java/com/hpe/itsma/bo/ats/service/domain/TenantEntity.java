package com.hpe.itsma.bo.ats.service.domain;

import com.hpe.itsma.bo.ats.service.domain.enumeration.*;
import com.hpe.itsma.bo.common.persistence.BaseEntity;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Created by sheyu on 2017/6/11.
 */
@Entity
@Table(name = "tenant_entity")
//@EntityListeners(AuditingEntityListener.class) //to wire the audit fields like createdBy
@Audited // to save audit db log into tenant_entity_aud
public class TenantEntity extends BaseEntity {

    public static final String ENTITY_TYPE = "Tenant";

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(name = "account_id")
    private Long account;

    @Enumerated(EnumType.STRING)
    @Column(name = "tenant_type")
    private TenantType tenantType;

    @Enumerated(EnumType.STRING)
    @Column(name = "tenant_state")
    private TenantState tenantState;

    @Column(name = "last_active_at")
    private LocalDateTime lastActiveDate;

    @Column(name = "tenant_environment")
    @Enumerated(EnumType.STRING)
    private TenantEnvironment tenantEnvironment;

    @Column(name = "product_instance")
    private Long productInstance;

    @Column(name = "suite_instance")
    private Long suiteInstance;

    @Column(name = "is_msp_tenant")
    private Boolean mspTenant;

    @Column(name = "is_managed_tenant")
    private Boolean managedTenant;

    @Column(name = "namespace_id")
    private Long namespace;

   /* @Column(name = "tenant_account_id")
    @ElementCollection
    @CollectionTable(name = "tenant_account_rel", joinColumns = @JoinColumn(name = "tenant_id", referencedColumnName = "id"))
    private List<Long> accountList;*/

    @Column(name = "deploy_type")
    @Enumerated(EnumType.STRING)
    private DeployType deployType;

    @Column(name = "tenant_contact_id")
    @ElementCollection
    @CollectionTable(name = "tenant_contact_rel", joinColumns = @JoinColumn(name = "tenant_id", referencedColumnName = "id"))
    private List<Long> contactList;

    @Column(name = "url")
    private String url;

    @Column(name = "service_of_product")
    private Long serviceOfProduct;

    @Column(name = "license_control_type")
    @Enumerated(EnumType.STRING)
    private LicenseControlType licenseControlType;

    @Column(name = "is_public")
    private Boolean accessControl ;

    @Column(name = "tenant_license_id")
    @ElementCollection
    @CollectionTable(name = "tenant_license_rel", joinColumns = @JoinColumn(name = "tenant_id", referencedColumnName = "id"))
    private List<Long> license;

    @Column(name = "tenant_admin_id")
    private String tenantAdminId;

    public TenantEntity() {
    }

    public TenantEntity(String name, Long account, TenantType tenantType, TenantState tenantState, LocalDateTime lastActiveDate, TenantEnvironment tenantEnvironment, Long productInstance, Long suiteInstance, Boolean mspTenant, Boolean managedTenant, Long namespace, DeployType deployType, List<Long> contactList, String url, Long serviceOfProduct, LicenseControlType licenseControlType, Boolean accessControl, List<Long> license, String tenantAdminId) {
        this.name = name;
        this.account = account;
        this.tenantType = tenantType;
        this.tenantState = tenantState;
        this.lastActiveDate = lastActiveDate;
        this.tenantEnvironment = tenantEnvironment;
        this.productInstance = productInstance;
        this.suiteInstance = suiteInstance;
        this.mspTenant = mspTenant;
        this.managedTenant = managedTenant;
        this.namespace = namespace;
        this.deployType = deployType;
        this.contactList = contactList;
        this.url = url;
        this.serviceOfProduct = serviceOfProduct;
        this.licenseControlType = licenseControlType;
        this.accessControl = accessControl;
        this.license = license;
        this.tenantAdminId = tenantAdminId;
    }

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

    public static String getEntityType() {
        return ENTITY_TYPE;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
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

    public LicenseControlType getLicenseControlType() {
        return licenseControlType;
    }

    public void setLicenseControlType(LicenseControlType licenseControlType) {
        this.licenseControlType = licenseControlType;
    }

    public List<Long> getLicense() {
        return license;
    }

    public void setLicense(List<Long> license) {
        this.license = license;
    }


    public List<Long> getContactList() {
        return contactList;
    }

    public void setContactList(List<Long> contactList) {
        this.contactList = contactList;
    }

    public Boolean getAccessControl() {
        return accessControl;
    }

    public void setAccessControl(Boolean accessControl) {
        this.accessControl = accessControl;
    }

    @Override
    public boolean allowManuallyAssignID() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TenantEntity that = (TenantEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(account, that.account) &&
                tenantType == that.tenantType &&
                tenantState == that.tenantState &&
                Objects.equals(lastActiveDate, that.lastActiveDate) &&
                tenantEnvironment == that.tenantEnvironment &&
                Objects.equals(productInstance, that.productInstance) &&
                Objects.equals(suiteInstance, that.suiteInstance) &&
                Objects.equals(mspTenant, that.mspTenant) &&
                Objects.equals(managedTenant, that.managedTenant) &&
                Objects.equals(namespace, that.namespace) &&
                deployType == that.deployType &&
                Objects.equals(contactList, that.contactList) &&
                Objects.equals(url, that.url) &&
                Objects.equals(serviceOfProduct, that.serviceOfProduct) &&
                licenseControlType == that.licenseControlType &&
                Objects.equals(accessControl, that.accessControl) &&
                Objects.equals(license, that.license) &&
                Objects.equals(tenantAdminId, that.tenantAdminId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, account, tenantType, tenantState, lastActiveDate, tenantEnvironment, productInstance, suiteInstance, mspTenant, managedTenant, namespace, deployType, contactList, url, serviceOfProduct, licenseControlType, accessControl, license, tenantAdminId);
    }
}
