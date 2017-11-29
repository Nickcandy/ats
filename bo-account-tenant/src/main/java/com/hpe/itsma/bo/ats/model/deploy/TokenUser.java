package com.hpe.itsma.bo.ats.model.deploy;

/**
 * Created by wxiaodon on 8/28/2017.
 */
public class TokenUser {
    private String loginName;
    private String password;

    public TokenUser(String loginName, String password) {
        this.loginName = loginName;
        this.password = password;
    }

    public TokenUser() {
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
