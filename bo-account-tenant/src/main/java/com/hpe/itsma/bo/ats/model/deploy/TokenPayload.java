package com.hpe.itsma.bo.ats.model.deploy;

/**
 * Created by wxiaodon on 8/24/2017.
 */
public class TokenPayload {
    private String username;
    private String password;

    public TokenPayload(String username, String password) {
        this.username = username;
        this.password = password;
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
