package com.hpe.itsma.bo.ats.service;

import com.hpe.itsma.bo.ats.service.domain.NamespaceEntity;
import com.hpe.itsma.bo.common.api.PageQueryEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by wxiaodon on 6/23/2017.
 */
@Transactional
@PreAuthorize("hasRole('FARM_ADMIN')")
public interface NamespaceService {

    PageQueryEntity<NamespaceEntity> getAllNameSpaces(int limit, int offset, List<Long> tenantIdList, List<String> orderByFieldList, String filter, boolean fetchTotalCount);

    NamespaceEntity getNamespaceById(Long namespaceId);

    NamespaceEntity createNamespaces(NamespaceEntity namespaceEntity);

    NamespaceEntity updateNamespace(Long id, NamespaceEntity namespace);

    PageQueryEntity<NamespaceEntity> searchNamespace(int limit, int offset, List<String> orderByFieldList, String keyWords, String filter, boolean fetchTotalCount);
}
