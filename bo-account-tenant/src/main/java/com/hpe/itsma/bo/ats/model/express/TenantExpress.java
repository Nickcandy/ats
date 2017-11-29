package com.hpe.itsma.bo.ats.model.express;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hpe.itsma.bo.ats.service.domain.enumeration.TenantEnvironment;
import com.hpe.itsma.bo.ats.service.domain.enumeration.TenantState;
import com.hpe.itsma.bo.ats.service.domain.enumeration.TenantType;
import com.hpe.itsma.bo.common.api.BaseModelExpress;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Created by wxiaodon on 8/11/2017.
 */
@ApiModel(value = "TenantExpress", description = "a pojo representing tenantExpress data for create tenant")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TenantExpress extends BaseModelExpress {

    @JsonProperty("name")
    @ApiModelProperty
    @NotNull
    protected String name = null;

    @ApiModelProperty
    @JsonProperty
    @NotNull
    private TenantType tenantType = null;

    @ApiModelProperty
    @JsonProperty
    @NotNull
    private TenantState tenantState = null;

    @ApiModelProperty
    @JsonProperty
    private Long account;

    @ApiModelProperty
    @JsonProperty
    private Boolean accessControl = true;

    @ApiModelProperty
    @JsonProperty
    private TenantEnvironment tenantEnvironment;

   /* @ApiModelProperty
    @JsonProperty
    private Long productInstance = null;

    @ApiModelProperty
    @JsonProperty
    private Long suiteInstance = null;*/

    @ApiModelProperty
    @JsonProperty
    private Boolean mspTenant = false;

    @ApiModelProperty
    @JsonProperty
    private Boolean managedTenant = false;

    @JsonProperty("tenantAdmin")
    @ApiModelProperty
    private String tenantAdminId;

    public String getTenantAdminId() {
        return tenantAdminId;
    }

    public void setTenantAdminId(String tenantAdminId) {
        this.tenantAdminId = tenantAdminId;
    }

    public TenantExpress() {
    }

    public TenantExpress(String name, TenantType tenantType, TenantState tenantState, Long account, Boolean accessControl, TenantEnvironment tenantEnvironment, Boolean mspTenant, Boolean managedTenant, String tenantAdminId) {
        this.name = name;
        this.tenantType = tenantType;
        this.tenantState = tenantState;
        this.account = account;
        this.accessControl = accessControl;
        this.tenantEnvironment = tenantEnvironment;
        this.mspTenant = mspTenant;
        this.managedTenant = managedTenant;
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

    public Boolean getAccessControl() {
        return accessControl;
    }

    public void setAccessControl(Boolean accessControl) {
        this.accessControl = accessControl;
    }

    public TenantEnvironment getTenantEnvironment() {
        return tenantEnvironment;
    }

    public void setTenantEnvironment(TenantEnvironment tenantEnvironment) {
        this.tenantEnvironment = tenantEnvironment;
    }

  /*  public Long getProductInstance() {
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
*/

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

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TenantExpress that = (TenantExpress) o;
        return Objects.equals(name, that.name) &&
                tenantType == that.tenantType &&
                tenantState == that.tenantState &&
                Objects.equals(account, that.account) &&
                Objects.equals(accessControl, that.accessControl) &&
                tenantEnvironment == that.tenantEnvironment &&
                Objects.equals(mspTenant, that.mspTenant) &&
                Objects.equals(managedTenant, that.managedTenant) &&
                Objects.equals(tenantAdminId, that.tenantAdminId);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (tenantType != null ? tenantType.hashCode() : 0);
        result = 31 * result + (tenantState != null ? tenantState.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (accessControl != null ? accessControl.hashCode() : 0);
        result = 31 * result + (tenantEnvironment != null ? tenantEnvironment.hashCode() : 0);
        result = 31 * result + (mspTenant != null ? mspTenant.hashCode() : 0);
        result = 31 * result + (managedTenant != null ? managedTenant.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TenantExpress{" +
                ", account=" + account +
                ", tenantType=" + tenantType +
                ", tenantState=" + tenantState +
                ", accessControl=" + accessControl +
                ", tenantEnvironment=" + tenantEnvironment +
                ", mspTenant=" + mspTenant +
                ", managedTenant=" + managedTenant +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", displayLabel='" + displayLabel + '\'' +
                '}';
    }
}
