package com.hpe.itsma.bo.ats.service;

import com.hpe.itsma.bo.ats.service.domain.ProductInstanceEntity;
import com.hpe.itsma.bo.common.api.PageQueryEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@PreAuthorize("hasRole('FARM_ADMIN')")
public interface ProductInstanceService {

    PageQueryEntity<ProductInstanceEntity> getAllProductInstances(long limit, long offset, String filter, List<String> orderBy, boolean fetchTotalCount);

    ProductInstanceEntity getProductInstanceById(Long suiteInstanceId);

    ProductInstanceEntity createProductInstance(ProductInstanceEntity suiteInstanceEntity);

    ProductInstanceEntity updateProductInstance(Long id, ProductInstanceEntity suiteInstanceEntity);

    void delete(long id);
}
