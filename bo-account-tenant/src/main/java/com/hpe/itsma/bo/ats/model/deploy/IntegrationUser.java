package com.hpe.itsma.bo.ats.model.deploy;

/**
 * Created by wxiaodon on 8/24/2017.
 */
public class IntegrationUser {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String username;

    private String password;

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

    public IntegrationUser(String username, String password) {
        this.username = username;
        this.id = username;
        this.password = password;
    }
}
