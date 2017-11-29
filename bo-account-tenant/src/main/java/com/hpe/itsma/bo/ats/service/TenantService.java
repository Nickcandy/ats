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
@PreAuthorize("hasRole('FARM_ADMIN')")
public interface TenantService {

    PageQueryEntity<TenantEntity> getAllTenants(int limit, int offset,List<String> orderByFieldList, String filter, boolean fetchTotalCount);

    TenantEntity getTenantById(Long tenantId);

    TenantEntity createTenants(TenantEntity tenantsList);

    TenantEntity updateTenant(Long id, TenantEntity aTenant);

    PageQueryEntity<TenantEntity> searchTenant(int limit, int offset, List<String> orderByFieldList, String keyWords, String filter, boolean fetchTotalCount);

    PageQueryEntity<TenantEntity> getAllPublicTenantsInAccountOrId(List<Long> accountId, List<Long> tenantIds, String filter, boolean fetchTotalCount);

    void delete(long id);

    int getChildenTenantsByAccountId(Long accoundId);

    boolean ifExistLiveRecWithSameName(String name, Long id);

    Boolean changeTenantState(TenantStateModel model);

    void changeTenantType(Long tenantId, TenantType tenantType);
}
