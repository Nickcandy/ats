package com.hpe.itsma.bo.ats.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hpe.itsma.bo.ats.service.domain.enumeration.*;
import com.hpe.itsma.bo.common.api.BaseModelPremium;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Account
 */
@ApiModel(value="Account", description="a pojo representing account data")
public class Account extends BaseModelPremium {

    @ApiModelProperty
    @NotNull
    protected String name = null;

    @ApiModelProperty
    @JsonProperty
    private LocalDateTime onBoardingTime;

    @ApiModelProperty
    @JsonProperty
    private AccountTier accountTier;

    @ApiModelProperty
    @JsonProperty
    private AccountStatus accountStatus;

    @ApiModelProperty
    @JsonProperty
    private AccountType accountType = null;

    @ApiModelProperty
    @JsonProperty
    private AccountRegion accountRegion;

    @ApiModelProperty
    @JsonProperty
    private String country;

    @ApiModelProperty
    @JsonProperty
    private String state;

    @ApiModelProperty
    @JsonProperty
    private String city;

    //TODO: wait until CONTACT entity is ready
    //private List<Long> contactList = new ArrayList<>();

    @ApiModelProperty
    @JsonProperty
    private Long primaryTenantId;

    @ApiModelProperty
    @JsonProperty
    @NotNull
    private Long customer = null;

    @ApiModelProperty
    @JsonProperty
    private Boolean enableSuiteSSO;

    @ApiModelProperty
    @JsonProperty
    private IntegrationType accountIntegrationType;

    //TODO: wait until SUITE INSTANCE entity is ready
    //private List<Long> suiteInstanceList;

    public Account() { }

    public Account id(Long id) {
        this.id = id;
        return this;
    }

//    public LocalDateTime getOnBoardingTime() {
//        return onBoardingTime;
//    }
//
//    public void setOnBoardingTime(LocalDateTime onBoardingTime) {
//        this.onBoardingTime = onBoardingTime;
//    }


    public Account(String name, LocalDateTime onBoardingTime, AccountTier accountTier, AccountStatus accountStatus, AccountType accountType, AccountRegion accountRegion, String country, String state, String city, Long primaryTenantId, Long customer, boolean enableSuiteSSO, IntegrationType accountIntegrationType) {
        this.name = name;
        this.onBoardingTime = onBoardingTime;
        this.accountTier = accountTier;
        this.accountStatus = accountStatus;
        this.accountType = accountType;
        this.accountRegion = accountRegion;
        this.country = country;
        this.state = state;
        this.city = city;
        this.primaryTenantId = primaryTenantId;
        this.customer = customer;
        this.enableSuiteSSO = enableSuiteSSO;
        this.accountIntegrationType = accountIntegrationType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
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

    public LocalDateTime getOnBoardingTime() {
        return onBoardingTime;
    }

    public void setOnBoardingTime(LocalDateTime onBoardingTime) {
        this.onBoardingTime = onBoardingTime;
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
        Account account = (Account) o;
        return enableSuiteSSO == account.enableSuiteSSO &&
                Objects.equals(name, account.name) &&
                Objects.equals(onBoardingTime, account.onBoardingTime) &&
                accountTier == account.accountTier &&
                accountStatus == account.accountStatus &&
                accountType == account.accountType &&
                accountRegion == account.accountRegion &&
                Objects.equals(country, account.country) &&
                Objects.equals(state, account.state) &&
                Objects.equals(city, account.city) &&
                Objects.equals(primaryTenantId, account.primaryTenantId) &&
                Objects.equals(customer, account.customer) &&
                accountIntegrationType == account.accountIntegrationType;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (onBoardingTime != null ? onBoardingTime.hashCode() : 0);
        result = 31 * result + (accountTier != null ? accountTier.hashCode() : 0);
        result = 31 * result + (accountStatus != null ? accountStatus.hashCode() : 0);
        result = 31 * result + (accountType != null ? accountType.hashCode() : 0);
        result = 31 * result + (accountRegion != null ? accountRegion.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (primaryTenantId != null ? primaryTenantId.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (enableSuiteSSO ? 1 : 0);
        result = 31 * result + (accountIntegrationType != null ? accountIntegrationType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", onBoardingTime=" + onBoardingTime +
                ", accountTier=" + accountTier +
                ", accountStatus=" + accountStatus +
                ", accountType=" + accountType +
                ", accountRegion=" + accountRegion +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", primaryTenantId=" + primaryTenantId +
                ", customer=" + customer +
                ", enableSuiteSSO=" + enableSuiteSSO +
                ", integrationType=" + accountIntegrationType +
                '}';
    }

    @Override
    protected String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

