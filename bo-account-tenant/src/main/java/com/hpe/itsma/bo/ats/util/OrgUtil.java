package com.hpe.itsma.bo.ats.util;

import com.hpe.itsma.bo.ats.model.IDMOrg;
import com.hpe.itsma.bo.ats.service.domain.enumeration.OrgType;
import org.hibernate.service.spi.ServiceException;

/**
 * Created by yhuang1 on 9/28/2017.
 */
public class OrgUtil {
    private static final String ORG_ID_SUFFIX_DB = "_db";
    private static final String ORG_ID_SUFFIX_LDAP = "_ldap";
    private static final String ORG_ID_SUFFIX_SAML = "_saml";

    private OrgUtil() {
        throw new IllegalStateException("OrgUtil class");
    }

    private static String getOrgId(long accountId, OrgType orgType) {
        String ret;
        if(orgType == OrgType.DB) {
            ret = accountId + ORG_ID_SUFFIX_DB;
        } else if(orgType == OrgType.LDAP) {
            ret = accountId + ORG_ID_SUFFIX_LDAP;
        } else if(orgType == OrgType.SAML){
            ret = accountId + ORG_ID_SUFFIX_SAML;
        } else {
            throw new ServiceException("Invalid org type: " + orgType);
        }
        return ret;
    }

    public static IDMOrg getIdmOrgInstance(long accountId, OrgType orgType) {
        IDMOrg ret = new IDMOrg();
        ret.setOrgType(orgType);
        ret.setOrgId(getOrgId(accountId,orgType));
        return ret;
    }
}
