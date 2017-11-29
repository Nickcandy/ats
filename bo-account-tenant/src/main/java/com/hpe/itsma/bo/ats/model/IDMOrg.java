package com.hpe.itsma.bo.ats.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hpe.itsma.bo.ats.service.domain.enumeration.IntegrationType;
import com.hpe.itsma.bo.ats.service.domain.enumeration.OrgType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by peijia on 7/21/2017 0021.
 */

@ApiModel(value="IDM Org", description="a pojo representing account's org data")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IDMOrg {
    @ApiModelProperty
    @JsonProperty
    private String orgId ;

    @ApiModelProperty
    @JsonProperty
    private OrgType orgType ;

    public IDMOrg() {
    }

    public IDMOrg(String orgId, OrgType orgType) {
        this.orgId = orgId;
        this.orgType = orgType;
    }

    public IDMOrg(String orgId, IntegrationType intType) {
        this.orgId = orgId;
        if(intType == IntegrationType.LDAP) {
            this.orgType = OrgType.LDAP;
        } else if(intType == IntegrationType.SAML) {
            this.orgType = OrgType.SAML;
        }
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public OrgType getOrgType() {
        return orgType;
    }

    public void setOrgType(OrgType orgType) {
        this.orgType = orgType;
    }
}
