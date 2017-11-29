package com.hpe.itsma.bo.ats.service;

import com.hpe.itsma.bo.ats.service.domain.SuiteInstanceEntity;
import com.hpe.itsma.bo.common.api.PageQueryEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@PreAuthorize("hasRole('FARM_ADMIN')")
public interface SuiteInstanceService {

    PageQueryEntity<SuiteInstanceEntity> getAllSuiteInstances(long limit, long offset, String filter, List<String> orderBy, boolean fetchTotalCount);

    SuiteInstanceEntity getSuiteInstanceById(Long suiteInstanceId);

    SuiteInstanceEntity createSuiteInstance(SuiteInstanceEntity suiteInstanceEntity);

    SuiteInstanceEntity updateSuiteInstance(Long id, SuiteInstanceEntity suiteInstanceEntity);

    void delete(long id);
}
