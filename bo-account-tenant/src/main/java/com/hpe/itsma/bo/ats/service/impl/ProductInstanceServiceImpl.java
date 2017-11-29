package com.hpe.itsma.bo.ats.service.impl;

import com.hpe.itsma.bo.ats.service.ProductInstanceService;
import com.hpe.itsma.bo.ats.service.domain.ProductInstanceEntity;
import com.hpe.itsma.bo.ats.service.repository.ProductInstanceEntityRepository;
import com.hpe.itsma.bo.common.api.PageQueryEntity;
import com.hpe.itsma.bo.common.exception.BoEntityNotFoundException;
import com.hpe.itsma.bo.common.filter.FilterBooleanExpression;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInstanceServiceImpl implements ProductInstanceService {

    @Autowired
    private ProductInstanceEntityRepository productInstanceEntityRepository;

    @Override
    public PageQueryEntity<ProductInstanceEntity> getAllProductInstances(long limit, long offset, String filter, List<String> orderBy, boolean fetchTotalCount) {
        FilterBooleanExpression<ProductInstanceEntity> filterBooleanExpression = new FilterBooleanExpression<>();
        BooleanExpression booleanExpression = filterBooleanExpression.getFilterBooleanExpression(ProductInstanceEntity.class, filter);
        return productInstanceEntityRepository.findAll(booleanExpression,(int)limit,(int)offset,orderBy,fetchTotalCount);
    }

    @Override
    public ProductInstanceEntity getProductInstanceById(Long productInstanceId) {
        ProductInstanceEntity productInstanceEntity = productInstanceEntityRepository.findOne(productInstanceId);
        if(productInstanceEntity == null){
            throw new BoEntityNotFoundException(ProductInstanceEntity.getEntityType(), String.valueOf(productInstanceId));
        }
        return productInstanceEntity;
    }

    @Override
    public ProductInstanceEntity createProductInstance(ProductInstanceEntity productInstanceEntity) {
        return productInstanceEntityRepository.save(productInstanceEntity);
    }

    @Override
    public ProductInstanceEntity updateProductInstance(Long id, ProductInstanceEntity anProductInstance) {
        if (!id.equals(anProductInstance.getId())) {
            throw new BoEntityNotFoundException(ProductInstanceEntity.getEntityType(), String.valueOf(id));
        }
        if (!productInstanceEntityRepository.exists(id)) {
            throw new BoEntityNotFoundException(ProductInstanceEntity.getEntityType(), String.valueOf(id));
        }
        return productInstanceEntityRepository.saveAndFlush(anProductInstance);
    }

    @Override
    public void delete(long id) {
        productInstanceEntityRepository.softDelete(id);
    }

}
