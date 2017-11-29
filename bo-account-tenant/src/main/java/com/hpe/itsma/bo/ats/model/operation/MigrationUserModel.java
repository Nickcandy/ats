package com.hpe.itsma.bo.ats.model.operation;

import java.util.List;

/**
 * Created by wxiaodon on 10/11/2017.
 */
public class MigrationUserModel {
    private String authType;

    private Boolean resetExternalId;

    private List<MigrationUserJSON> users;

    public MigrationUserModel() {
    }

    public MigrationUserModel(String authType, Boolean resetExternalId, List<MigrationUserJSON> users) {
        this.authType = authType;
        this.resetExternalId = resetExternalId;
        this.users = users;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public Boolean getResetExternalId() {
        return resetExternalId;
    }

    public void setResetExternalId(Boolean resetExternalId) {
        this.resetExternalId = resetExternalId;
    }

    public List<MigrationUserJSON> getUsers() {
        return users;
    }

    public void setUsers(List<MigrationUserJSON> users) {
        this.users = users;
    }
}
