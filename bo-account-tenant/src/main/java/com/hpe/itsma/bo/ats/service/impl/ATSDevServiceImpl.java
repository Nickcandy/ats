package com.hpe.itsma.bo.ats.service.impl;

import com.hpe.itsma.bo.ats.service.ATSDevService;
import com.hpe.itsma.bo.ats.service.domain.TenantEntity;
import com.hpe.itsma.bo.ats.service.repository.TenantEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by sheyu on 2017/6/11.
 */
@Service
@Profile({"dev", "devpg", "kube"})
public class ATSDevServiceImpl implements ATSDevService {
    @Autowired
    private TenantEntityRepository tenantEntityRepository;

    @Override
    public TenantEntity createTenants(TenantEntity aTenant) {
        return tenantEntityRepository.save(aTenant);
    }

}
