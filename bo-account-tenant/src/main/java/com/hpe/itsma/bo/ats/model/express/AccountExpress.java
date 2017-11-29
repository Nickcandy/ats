package com.hpe.itsma.bo.ats.model.express;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hpe.itsma.bo.ats.service.domain.enumeration.*;
import com.hpe.itsma.bo.common.api.BaseModelExpress;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;


/**
 * Created by wxiaodon on 8/11/2017.
 */
@ApiModel(value = "AccountExpress", description = "a pojo representing accountExpress data for create account")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountExpress extends BaseModelExpress{

    @JsonProperty("name")
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
    private AccountType accountType;

    @ApiModelProperty
    @JsonProperty
    private AccountRegion accountRegion;


    //TODO: wait until CONTACT entity is ready
    //private List<Long> contactList = new ArrayList<>();

    @ApiModelProperty
    @JsonProperty("owner")
    private String ownerId;

    //TODO: wait until SUITE INSTANCE entity is ready
    //private List<Long> suiteInstanceList;

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

    public AccountExpress() { }

    public AccountExpress(String name, LocalDateTime onBoardingTime, AccountTier accountTier, AccountStatus accountStatus, AccountType accountType, AccountRegion accountRegion, String ownerId, Long customer, Boolean enableSuiteSSO, IntegrationType accountIntegrationType) {
        this.name = name;
        this.onBoardingTime = onBoardingTime;
        this.accountTier = accountTier;
        this.accountStatus = accountStatus;
        this.accountType = accountType;
        this.accountRegion = accountRegion;
        this.ownerId = ownerId;
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

    public LocalDateTime getOnBoardingTime() {
        return onBoardingTime;
    }

    public void setOnBoardingTime(LocalDateTime onBoardingTime) {
        this.onBoardingTime = onBoardingTime;
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

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
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
        AccountExpress that = (AccountExpress) o;
        return enableSuiteSSO == that.enableSuiteSSO &&
                Objects.equals(name, that.name) &&
                Objects.equals(onBoardingTime, that.onBoardingTime) &&
                accountTier == that.accountTier &&
                accountStatus == that.accountStatus &&
                accountType == that.accountType &&
                accountRegion == that.accountRegion &&
                Objects.equals(ownerId, that.ownerId) &&
                Objects.equals(customer, that.customer) &&
                accountIntegrationType == that.accountIntegrationType;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (onBoardingTime != null ? onBoardingTime.hashCode() : 0);
        result = 31 * result + (accountTier != null ? accountTier.hashCode() : 0);
        result = 31 * result + (accountStatus != null ? accountStatus.hashCode() : 0);
        result = 31 * result + (accountType != null ? accountType.hashCode() : 0);
        result = 31 * result + (accountRegion != null ? accountRegion.hashCode() : 0);
        result = 31 * result + (ownerId != null ? ownerId.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (enableSuiteSSO ? 1 : 0);
        result = 31 * result + (accountIntegrationType != null ? accountIntegrationType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AccountExpress{" +
                "name='" + name + '\'' +
                ", onBoardingTime=" + onBoardingTime +
                ", accountTier=" + accountTier +
                ", accountStatus=" + accountStatus +
                ", accountType=" + accountType +
                ", accountRegion=" + accountRegion +
                ", ownerId='" + ownerId + '\'' +
                ", customer=" + customer +
                ", enableSuiteSSO=" + enableSuiteSSO +
                ", integrationType=" + accountIntegrationType +
                '}';
    }
}
