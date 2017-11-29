package com.hpe.itsma.bo.ats.agent;

import com.hpe.itsma.bo.common.security.UserDetailsImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * Created by wxiaodon on 10/24/2017.
 */
public abstract class BaseBoAgent extends BaseAgent{

    @Override
    protected HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        Optional.ofNullable(securityContext.getAuthentication()).ifPresent(authentication -> {
            String ssoToken = ((UserDetailsImpl) authentication.getDetails()).getSsoToken();
            headers.set("Cookie", "LWSSO_COOKIE_KEY=" + ssoToken);
        });
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

}
