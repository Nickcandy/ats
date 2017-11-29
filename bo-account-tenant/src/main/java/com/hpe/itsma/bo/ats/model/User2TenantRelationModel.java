package com.hpe.itsma.bo.ats.model;

import java.util.List;

/**
 * Created by wxiaodon on 9/26/2017.
 */
public class User2TenantRelationModel {
    private Long tenantID;
    private List<Long> add;
    private List<Long> delete;

    public User2TenantRelationModel() {
    }

    public User2TenantRelationModel(Long tenantID, List<Long> add, List<Long> delete) {
        this.tenantID = tenantID;
        this.add = add;
        this.delete = delete;
    }

    public Long getTenantID() {
        return tenantID;
    }

    public void setTenantID(Long tenantID) {
        this.tenantID = tenantID;
    }

    public List<Long> getAdd() {
        return add;
    }

    public void setAdd(List<Long> add) {
        this.add = add;
    }

    public List<Long> getDelete() {
        return delete;
    }

    public void setDelete(List<Long> delete) {
        this.delete = delete;
    }
}
