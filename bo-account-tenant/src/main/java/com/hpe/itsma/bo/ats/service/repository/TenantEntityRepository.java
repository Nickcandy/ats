package com.hpe.itsma.bo.ats.service.repository;

import com.hpe.itsma.bo.ats.service.domain.TenantEntity;
import com.hpe.itsma.bo.ats.service.domain.enumeration.TenantState;
import com.hpe.itsma.bo.common.repository.EntityAuditRepository;
import com.hpe.itsma.bo.common.repository.EntityRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Created by sheyu on 2017/6/11.
 */
public interface TenantEntityRepository extends EntityRepository<TenantEntity, Long>, EntityAuditRepository{

    @Modifying
    @Transactional
    @Query("UPDATE TenantEntity t SET t.tenantState = ?2 where t.id = ?1")
    void updateTenantState(Long tenantId, TenantState tenantStateEnum);

    @Query("SELECT t.lastActiveDate from TenantEntity t  where t.id = ?1")
    LocalDateTime getLastActiveDate(Long tenantId);

    @Query("SELECT t from TenantEntity t where t.id in (?1) and t.sysData=false")
    List<TenantEntity> getMyTenants(List<Long> ids);

    @Query("SELECT t.id from TenantEntity t")
    List<Long> getAllTenantIds();

    @Modifying
    @Transactional
    @Query("UPDATE TenantEntity t SET t.lastActiveDate = ?2 where t.id = ?1")
    void setTenantLastActiveDate(Long tenantId, LocalDateTime activeDate);

    @Query("SELECT count(a.id) from TenantEntity a  where a.account=?1 and a.deleted=false")
    int getChildenTenantsByAccountId(Long accoundId);

    @Query("SELECT count(a.id) from TenantEntity a  where a.name=?1 and a.deleted=false and a.id<>?2")
    int countOfRecWithSameName(String name, Long id);

    @Query("SELECT t.tenantState from TenantEntity t  where t.id = ?1")
    TenantState getTenantState(Long tenantId);
}
