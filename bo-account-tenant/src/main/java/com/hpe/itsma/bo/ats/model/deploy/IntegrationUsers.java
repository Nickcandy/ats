package com.hpe.itsma.bo.ats.model.deploy;

/**
 * Created by wxiaodon on 6/29/2017.
 */
public class IntegrationUsers {

    private IntegrationUser internal;

    private IntegrationUser external;

    public IntegrationUsers(IntegrationUser internal, IntegrationUser external) {
        this.internal = internal;
        this.external = external;
    }

    @Override
    public String toString() {
        return "IntegrationUsers{" +
                "internal=" + internal +
                ", external=" + external +
                '}';
    }

    public IntegrationUser getInternal() {
        return internal;
    }

    public void setInternal(IntegrationUser internal) {
        this.internal = internal;
    }

    public IntegrationUser getExternal() {
        return external;
    }

    public void setExternal(IntegrationUser external) {
        this.external = external;
    }
}
