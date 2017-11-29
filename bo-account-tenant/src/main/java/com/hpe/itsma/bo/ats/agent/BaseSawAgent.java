package com.hpe.itsma.bo.ats.agent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * Created by wxiaodon on 10/24/2017.
 */
public abstract class BaseSawAgent extends BaseAgent{

    @Autowired
    private SawIdmAgent idmAgent;

    @Override
    protected HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", String.format("LWSSO_COOKIE_KEY=%s", idmAgent.getIdmToken()));
        return headers;
    }

}
