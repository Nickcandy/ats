package com.hpe.itsma.bo.ats.service;

import com.hpe.itsma.bo.ats.model.deploy.TenantStateModel;
import com.hpe.itsma.bo.ats.service.domain.TenantEntity;
import com.hpe.itsma.bo.ats.service.domain.enumeration.TenantType;
import com.hpe.itsma.bo.common.api.PageQueryEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by sheyu on 2017/6/11.
 */

@Transactional
@PreAuthorize("hasAnyRole('FARM_ADMIN','TENANT_USER')")
public interface MyTenantService {
    PageQueryEntity<TenantEntity> getMyTenants();
}
