package com.hpe.itsma.bo.ats.service.repository;

import com.hpe.itsma.bo.ats.service.domain.CustomerEntity;
import com.hpe.itsma.bo.common.repository.EntityRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CustomerEntityRepository extends EntityRepository<CustomerEntity, Long> {
    @Query("SELECT a from CustomerEntity a  where (a.shortName=?1 or a.fullName=?2) and a.deleted=false and id<>?3")
    List<CustomerEntity> getCustomersWithDuplicateName(String shortName, String fullName, Long id);
}
