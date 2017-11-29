package com.hpe.itsma.bo.ats.service.domain;

import com.hpe.itsma.bo.ats.service.domain.enumeration.*;
import com.hpe.itsma.bo.common.persistence.BaseEntity;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "account_entity")
@Audited // to save audit db log into tenant_entity_aud
public class AccountEntity extends BaseEntity{

    private static final String ENTITY_TYPE = "Account";

    @Column(name = "onboarding_time")
    private LocalDateTime onBoardingTime = null;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_tier")
    private AccountTier accountTier;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status")
    private AccountStatus accountStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_region")
    private AccountRegion accountRegion;

    @Column(name = "country")
    private String country;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;

    //TODO: wait until CONTACT entity is ready
    //@Column(name = "contact_id")
    //@ElementCollection
    //@CollectionTable(name = "account_contact_rel", joinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "id"))
    //private List<Long> contactList = new ArrayList<>();

    @Column(name = "primary_tenant_id")
    private Long primaryTenantId;

    //TODO: wait until SUITE INSTANCE entity is ready
    //@Column(name = "suite_instance_list")
    //@ElementCollection
    //@CollectionTable(name = "account_suite_rel", joinColumns = @JoinColumn(name = "suite_id", referencedColumnName = "id"))
    //private List<Long> suiteInstanceList;

    @Column(name = "customer")
    private Long customer;

    @Column(name = "enable_suite_sso")
    private Boolean enableSuiteSSO;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_integration_type")
    private IntegrationType accountIntegrationType;

    @Override
    public boolean allowManuallyAssignID() {
        return true;
    }

    public LocalDateTime getOnBoardingTime() {
        return onBoardingTime;
    }

    public void setOnBoardingTime(LocalDateTime onBoardingTime) {
        this.onBoardingTime = onBoardingTime;
    }

    public AccountTier getAccountTier() {
        return accountTier;
    }

    public void setAccountTier(AccountTier accountTier) {
        this.accountTier = accountTier;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public AccountRegion getAccountRegion() {
        return accountRegion;
    }

    public void setAccountRegion(AccountRegion accountRegion) {
        this.accountRegion = accountRegion;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getPrimaryTenantId() {
        return primaryTenantId;
    }

    public void setPrimaryTenantId(Long primaryTenantId) {
        this.primaryTenantId = primaryTenantId;
    }

    public static String getEntityType() {
        return ENTITY_TYPE;
    }

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }

    public Boolean getEnableSuiteSSO() {
        return enableSuiteSSO;
    }

    public void setEnableSuiteSSO(Boolean enableSuiteSSO) {
        this.enableSuiteSSO = enableSuiteSSO;
    }

    public IntegrationType getAccountIntegrationType() {
        return accountIntegrationType;
    }

    public void setAccountIntegrationType(IntegrationType accountIntegrationType) {
        this.accountIntegrationType = accountIntegrationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AccountEntity that = (AccountEntity) o;
        return enableSuiteSSO == that.enableSuiteSSO &&
                Objects.equals(onBoardingTime, that.onBoardingTime) &&
                accountTier == that.accountTier &&
                accountStatus == that.accountStatus &&
                accountType == that.accountType &&
                accountRegion == that.accountRegion &&
                Objects.equals(country, that.country) &&
                Objects.equals(state, that.state) &&
                Objects.equals(city, that.city) &&
                Objects.equals(primaryTenantId, that.primaryTenantId) &&
                Objects.equals(customer, that.customer) &&
                accountIntegrationType == that.accountIntegrationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onBoardingTime, accountTier, accountStatus, accountType, accountRegion, country, state, city, primaryTenantId, customer, enableSuiteSSO, accountIntegrationType);
    }
}
