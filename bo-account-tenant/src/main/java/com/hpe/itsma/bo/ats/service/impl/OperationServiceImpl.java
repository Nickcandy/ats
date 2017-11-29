package com.hpe.itsma.bo.ats.service.impl;

import com.hpe.itsma.bo.ats.service.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by xuyuf on 2017/7/19.
 */
@Service
public class OperationServiceImpl implements OperationService {
    Logger logger = LoggerFactory.getLogger(OperationServiceImpl.class);

    @Override
    public String getOperationalTenantInfo() {
        return "{\"operationInfo\":" +
                  "{\"tenantId\":10000," +
                  "\"sawBaseUrl\":\"http://127.0.0.1\"}" +
                 "}";
    }
}
