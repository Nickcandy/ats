package com.hpe.itsma.bo.ats.util;

/**
 * Created by wxiaodon on 10/25/2017.
 */
public class LicenseUtil {

    private static final int EDITION_EXPRESS = 1;
    private static final int EDITION_PREMIUM = 2;
    private static final int ACCESS_CONCURRENT = 1;
    private static final int ACCESS_NAMED = 2;
    private static final String MODLE_EXPRESS_CONCURRENT = "Express edition concurrent user";
    private static final String MODLE_EXPRESS_NAMED = "Express edition named user";
    private static final String MODLE_PREMIUM_CONCURRENT = "Premium edition concurrent user";
    private static final String MODLE_PREMIUM_NAMED = "Premium edition named user";

    private static final int MODE_TRY = 1;
    private static final int MODE_PROD = 2;
    private static final int MODE_EVAL = 3;
    private static final String TYPE_TRY = "TRY";
    private static final String TYPE_PROD = "PRODUCTION";
    private static final String TYPE_EVAL = "EVALUATION";

    private LicenseUtil() {
        throw new IllegalStateException("LicenseUtil class");
    }

    public static String getLicenseModel(Integer edition, Integer accessType) {
        if (EDITION_EXPRESS == edition) {
            if (ACCESS_CONCURRENT == accessType) { return MODLE_EXPRESS_CONCURRENT; }
            if (ACCESS_NAMED == accessType) { return MODLE_EXPRESS_NAMED; }
        }
        if (EDITION_PREMIUM == edition) {
            if (ACCESS_CONCURRENT == accessType) { return MODLE_PREMIUM_CONCURRENT; }
            if (ACCESS_NAMED == accessType) { return MODLE_PREMIUM_NAMED; }
        }
        throw new IllegalArgumentException("Cannot get license feature name by edition and access type");
    }

    public static String getLicenseType(Integer mode) {
        if (MODE_TRY == mode) { return TYPE_TRY; }
        if (MODE_PROD == mode) { return TYPE_PROD; }
        if (MODE_EVAL == mode) { return TYPE_EVAL; }
        return TYPE_TRY;
    }
}
