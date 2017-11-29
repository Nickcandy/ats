package com.hpe.itsma.bo.ats.service.impl;

import com.hpe.itsma.bo.ats.service.TokenService;
import com.hpe.itsma.bo.ats.agent.BoGatewayAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wxiaodon on 8/28/2017.
 */
@Service
public class TokenServiceImpl implements TokenService{

    @Autowired
    private BoGatewayAgent agent;

    @Override
    public String getToken(String username, String password) {
        return agent.getToken(username, password);
    }
}
