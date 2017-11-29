package com.hpe.itsma.bo.ats.model.deploy;

import com.hpe.itsma.bo.ats.service.domain.enumeration.TenantState;

import javax.validation.constraints.NotNull;

/**
 * Created by wxiaodon on 10/11/2017.
 */
public class TenantStateModel {
    @NotNull
    private Long tenantId = null;

    @NotNull
    private TenantState tenantState = null;

    public TenantStateModel() {
    }

    public TenantStateModel(Long tenantId, TenantState tenantState) {
        this.tenantId = tenantId;
        this.tenantState = tenantState;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public TenantState getTenantState() {
        return tenantState;
    }

    public void setTenantState(TenantState tenantState) {
        this.tenantState = tenantState;
    }
}
