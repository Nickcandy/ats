package com.hpe.itsma.bo.ats.service.repository;

import com.hpe.itsma.bo.ats.service.domain.AccountEntity;
import com.hpe.itsma.bo.common.repository.EntityRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountEntityRepository extends EntityRepository<AccountEntity, Long> {
    @Query("SELECT count(a.id) from AccountEntity a  where a.enableSuiteSSO=true and a.deleted=false")
    int getSSOEnabledAccounts();

    @Query("SELECT count(a.id) from AccountEntity a  where a.customer=?1 and a.deleted=false")
    int getChildenAccountByCustomerId(Long customerId);

    @Query("SELECT count(a.id) from AccountEntity a  where a.name=?1 and a.deleted=false and a.id<>?2")
    int countOfRecWithSameName(String name, Long id);

    @Query("SELECT t.id from AccountEntity t")
    List<Long> getAllAccountIds();
}
