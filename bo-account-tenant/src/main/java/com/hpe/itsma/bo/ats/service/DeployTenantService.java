package com.hpe.itsma.bo.ats.service;

import com.hpe.itsma.bo.ats.model.deploy.DeployTenantResponse;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.transaction.Transactional;

/**
 * Created by wxiaodon on 10/20/2017.
 */
@Transactional
@PreAuthorize("hasRole('FARM_ADMIN')")
public interface DeployTenantService {

    DeployTenantResponse deployTenant(Long tenantId, Long accountId, Long tenantAdminUserId);

    @PreAuthorize("permitAll()")
    void deployTenantCallback(DeployTenantResponse deployTenantResponse);
}
