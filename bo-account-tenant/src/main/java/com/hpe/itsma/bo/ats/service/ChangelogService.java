package com.hpe.itsma.bo.ats.service;

import com.hpe.itsma.bo.common.api.PageQueryObject;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.transaction.Transactional;

/**
 * Created by wxiaodon on 9/7/2017.
 */
@Transactional
@PreAuthorize("hasRole('FARM_ADMIN')")
public interface ChangelogService {

    Object isChangeOfAttributeChangeLog(String entityType, String fieldName, Long entityId, Long lastChangeDateTime);

    PageQueryObject<Object> listValueOfAttributeChangelog(String entityType, String fieldName, Long entityId, Long lastChangeDateTime);

}
