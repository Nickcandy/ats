package com.hpe.itsma.bo.ats.model;

/**
 * Created by wxiaodon on 8/28/2017.
 */
public class BoTokenUser {
    private String username;
    private String password;

    public BoTokenUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public BoTokenUser() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
