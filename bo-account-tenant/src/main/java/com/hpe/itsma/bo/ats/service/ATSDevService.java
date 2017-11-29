package com.hpe.itsma.bo.ats.service;

import com.hpe.itsma.bo.ats.service.domain.TenantEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.transaction.Transactional;

/**
 * Created by sheyu on 2017/6/11.
 */

@Transactional
@PreAuthorize("hasRole('FARM_ADMIN')")
@Profile({"dev", "devpg", "kube"})
public interface ATSDevService {
    TenantEntity createTenants(TenantEntity tenantsList);
}
