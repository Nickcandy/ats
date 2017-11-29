package com.hpe.itsma.bo.ats.util;

/**
 * Created by yhuang1 on 9/28/2017.
 */
public class ATSConstants {

    private ATSConstants() {
        throw new IllegalStateException("ATSConstants class");
    }

    public static final String ORG_ID_SUITE_INTEGRATION = "itsma";
    public static final String ORG_ID_SYSTEM_BACK_OFFICE = "sysbo";

    public static final String ERROT_TENANT_NAME_UNIQUE = "error.server.tenant_name_not_unique";
    public static final String ERROR_ACCOUNT_NAME_UNIQUE = "error.server.account_name_not_unique";
    public static final String ERROR_ACCOUNT_TENANT_DELETE = "error.server.cannot_delete_account_used_by_tenants";
    public static final String ERROR_ACCOUNT_USER_DELETE = "error.server.cannot_delete_account_used_by_users";

    public static final long OOTB_SYSTEM_TENANT_ID = 10000L;
}
