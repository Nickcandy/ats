package com.hpe.itsma.bo.ats.service.impl;

import com.hpe.itsma.bo.ats.service.SuiteInstanceService;
import com.hpe.itsma.bo.ats.service.domain.SuiteInstanceEntity;
import com.hpe.itsma.bo.ats.service.repository.SuiteInstanceEntityRepository;
import com.hpe.itsma.bo.common.api.PageQueryEntity;
import com.hpe.itsma.bo.common.exception.BoEntityNotFoundException;
import com.hpe.itsma.bo.common.exception.BoInvalidParameterException;
import com.hpe.itsma.bo.common.filter.FilterBooleanExpression;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SuiteInstanceServiceImpl implements SuiteInstanceService {

    @Autowired
    private SuiteInstanceEntityRepository suiteInstanceEntityRepository;

    @Override
    public PageQueryEntity<SuiteInstanceEntity> getAllSuiteInstances(long limit, long offset, String filter, List<String> orderBy, boolean fetchTotalCount) {
        FilterBooleanExpression<SuiteInstanceEntity> filterBooleanExpression = new FilterBooleanExpression<>();
        BooleanExpression booleanExpression = filterBooleanExpression.getFilterBooleanExpression(SuiteInstanceEntity.class, filter);
        return suiteInstanceEntityRepository.findAll(booleanExpression,(int)limit,(int)offset,orderBy,fetchTotalCount);
    }

    @Override
    public SuiteInstanceEntity getSuiteInstanceById(Long suiteInstanceId) {
        SuiteInstanceEntity suiteInstanceEntity = suiteInstanceEntityRepository.findOne(suiteInstanceId);
        if(suiteInstanceEntity == null){
            throw new BoEntityNotFoundException(SuiteInstanceEntity.getEntityType(), String.valueOf(suiteInstanceId));
        }
        return suiteInstanceEntity;
    }

    @Override
    public SuiteInstanceEntity createSuiteInstance(SuiteInstanceEntity suiteInstanceEntity) {
        return suiteInstanceEntityRepository.save(suiteInstanceEntity);
    }

    public SuiteInstanceEntity updateSuiteInstance(Long id, SuiteInstanceEntity anSuiteInstance) {
        if (!id.equals(anSuiteInstance.getId())) {
            throw new BoInvalidParameterException(Arrays.asList("id in url", "id in payload").toString(), Arrays.asList(id, anSuiteInstance.getId()).toString());
        }
        if (!suiteInstanceEntityRepository.exists(id)) {
            throw new BoEntityNotFoundException(SuiteInstanceEntity.getEntityType(), String.valueOf(id));
        }
        return suiteInstanceEntityRepository.saveAndFlush(anSuiteInstance);
    }

    @Override
    public void delete(long id) {
        suiteInstanceEntityRepository.softDelete(id);
    }

}
