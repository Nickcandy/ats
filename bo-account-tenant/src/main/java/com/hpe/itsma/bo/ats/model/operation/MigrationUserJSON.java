package com.hpe.itsma.bo.ats.model.operation;

/**
 * Created by wxiaodon on 10/12/2017.
 */
public class MigrationUserJSON {
    private String email;
    private String loginName;
    private String firstName;
    private String familyName;
    private String middleName;
    private String authType;
    private String homePhoneNumber;
    private String officePhoneNumber;
    private String mobilePhoneNumber;
    private String locale;
    private String userType;

    public MigrationUserJSON() {
    }

    public MigrationUserJSON(String email, String loginName, String firstName, String familyName, String middleName, String authType, String homePhoneNumber, String officePhoneNumber, String mobilePhoneNumber, String locale, String userType) {
        this.email = email;
        this.loginName = loginName;
        this.firstName = firstName;
        this.familyName = familyName;
        this.middleName = middleName;
        this.authType = authType;
        this.homePhoneNumber = homePhoneNumber;
        this.officePhoneNumber = officePhoneNumber;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.locale = locale;
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public String getOfficePhoneNumber() {
        return officePhoneNumber;
    }

    public void setOfficePhoneNumber(String officePhoneNumber) {
        this.officePhoneNumber = officePhoneNumber;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
