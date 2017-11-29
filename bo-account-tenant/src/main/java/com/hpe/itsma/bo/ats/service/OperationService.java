package com.hpe.itsma.bo.ats.service;

import org.springframework.security.access.prepost.PreAuthorize;

import javax.transaction.Transactional;

/**
 * Created by xuyuf on 2017/7/19.
 */
@Transactional
@PreAuthorize("hasRole('FARM_ADMIN')")
public interface OperationService {
    String getOperationalTenantInfo();
}
