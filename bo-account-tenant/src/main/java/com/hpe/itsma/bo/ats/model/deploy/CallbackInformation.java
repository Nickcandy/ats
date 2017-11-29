package com.hpe.itsma.bo.ats.model.deploy;

/**
 * Created by wxiaodon on 6/29/2017.
 */
public class CallbackInformation {

    private String host;

    private String port;

    private String url;

    public CallbackInformation() {
    }

    public CallbackInformation(String host, String port, String url) {
        this.host = host;
        this.port = port;
        this.url = url;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "CallbackInformation{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
