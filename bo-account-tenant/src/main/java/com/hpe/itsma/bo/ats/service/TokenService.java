package com.hpe.itsma.bo.ats.service;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by wxiaodon on 8/28/2017.
 */
public interface TokenService {
    @PreAuthorize("permitAll()")
    String getToken (String username, String password);
}
