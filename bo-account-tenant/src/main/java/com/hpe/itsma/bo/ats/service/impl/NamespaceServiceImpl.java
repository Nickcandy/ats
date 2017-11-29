package com.hpe.itsma.bo.ats.service.impl;

import com.hpe.itsma.bo.ats.service.NamespaceService;
import com.hpe.itsma.bo.ats.service.domain.NamespaceEntity;
import com.hpe.itsma.bo.ats.service.domain.QNamespaceEntity;
import com.hpe.itsma.bo.ats.service.repository.NamespaceEntityRepository;
import com.hpe.itsma.bo.common.api.PageQueryEntity;
import com.hpe.itsma.bo.common.exception.BoEntityNotFoundException;
import com.hpe.itsma.bo.common.exception.BoInvalidParameterException;
import com.hpe.itsma.bo.common.filter.FilterBooleanExpression;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


/**
 * Created by wxiaodon on 6/23/2017.
 */
@Service
public class NamespaceServiceImpl implements NamespaceService {

    @Autowired
    private NamespaceEntityRepository namespaceEntityRepository;

    @Override
    public PageQueryEntity<NamespaceEntity> getAllNameSpaces(int limit, int offset, List<Long> tenantIdList, List<String> orderByFieldList, String filter, boolean fetchTotalCount) {
         BooleanBuilder builder = new BooleanBuilder();
        builder.and(tenantIdList == null || tenantIdList.isEmpty() ? Expressions.ONE.eq(1) : QNamespaceEntity.namespaceEntity.id.in(tenantIdList));
        FilterBooleanExpression<NamespaceEntity> filterBooleanExpression = new FilterBooleanExpression<>();
        BooleanExpression booleanExpression = filterBooleanExpression.getFilterBooleanExpression(NamespaceEntity.class, filter);
        builder.and(booleanExpression);
        return namespaceEntityRepository.findAll(builder, limit, offset, orderByFieldList,fetchTotalCount);
    }

    @Override
    public NamespaceEntity getNamespaceById(Long namespaceId) {
        NamespaceEntity namespaceEntity = namespaceEntityRepository.findOne(namespaceId);
        if (namespaceEntity == null) {
            throw new BoEntityNotFoundException(NamespaceEntity.getEntityType(), String.valueOf(namespaceId));
        }
        return namespaceEntity;
    }

    @Override
    public NamespaceEntity createNamespaces(NamespaceEntity namespaceEntity) {
        return namespaceEntityRepository.save(namespaceEntity);
    }

    @Override
    public NamespaceEntity updateNamespace(Long id, NamespaceEntity namespace) {
        if (!id.equals(namespace.getId())) {
            throw new BoInvalidParameterException(Arrays.asList("id in url","id in payload").toString(), Arrays.asList(id,namespace.getId()).toString());
        }
        if (!namespaceEntityRepository.exists(id)) {
            throw new BoEntityNotFoundException(NamespaceEntity.getEntityType(), String.valueOf(id));
        }
        return namespaceEntityRepository.saveAndFlush(namespace);
    }

    @Override
    public PageQueryEntity<NamespaceEntity> searchNamespace(int limit, int offset, List<String> orderByFieldList, String keyWords, String filter, boolean fetchTotalCount) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.or(QNamespaceEntity.namespaceEntity.description.contains(keyWords))
                .or(QNamespaceEntity.namespaceEntity.displayLabel.contains(keyWords))
                .or(QNamespaceEntity.namespaceEntity.name.contains(keyWords));
        FilterBooleanExpression<NamespaceEntity> filterBooleanExpression = new FilterBooleanExpression<>();
        BooleanExpression booleanExpression = filterBooleanExpression.getFilterBooleanExpression(NamespaceEntity.class, filter);
        booleanBuilder.and(booleanExpression);
        return namespaceEntityRepository.findAll(booleanBuilder, limit, offset, orderByFieldList,fetchTotalCount);
    }
}
