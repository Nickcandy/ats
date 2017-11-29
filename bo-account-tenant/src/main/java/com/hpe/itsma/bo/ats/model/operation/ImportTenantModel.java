package com.hpe.itsma.bo.ats.model.operation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wxiaodon on 9/25/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImportTenantModel {
    @JsonProperty
    private Long tenantId;

    @JsonProperty
    private String filePath;

    @JsonProperty
    private Boolean importRoleAndPermissions;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Boolean getImportRoleAndPermissions() {
        return importRoleAndPermissions;
    }

    public void setImportRoleAndPermissions(Boolean importRoleAndPermissions) {
        this.importRoleAndPermissions = importRoleAndPermissions;
    }

    public ImportTenantModel(Long tenantId, String filePath, Boolean importRoleAndPermissions) {
        this.tenantId = tenantId;
        this.filePath = filePath;
        this.importRoleAndPermissions = importRoleAndPermissions;
    }

    public ImportTenantModel() {
    }
}
