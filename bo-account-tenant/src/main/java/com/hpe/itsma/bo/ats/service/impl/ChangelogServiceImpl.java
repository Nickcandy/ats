package com.hpe.itsma.bo.ats.service.impl;

import com.hpe.itsma.bo.ats.service.ChangelogService;
import com.hpe.itsma.bo.ats.service.repository.TenantEntityRepository;
import com.hpe.itsma.bo.common.api.PageQueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.TimeZone;

/**
 * Created by wxiaodon on 9/7/2017.
 */
@Service
public class ChangelogServiceImpl implements ChangelogService {

    @Autowired
    private TenantEntityRepository tenantEntityRepository;

    private LocalDateTime longToLocalDateTime(Long dataTime){
        return dataTime == null ? null : LocalDateTime.ofInstant(Instant.ofEpochMilli(dataTime), TimeZone.getTimeZone(ZoneOffset.UTC.getId()).toZoneId());
    }

    private String getFullClassName(String entityType){
        return "com.hpe.itsma.bo.ats.service.domain." + entityType + "Entity";
    }

    @Override
    public Object isChangeOfAttributeChangeLog(String entityType, String fieldName, Long entityId, Long lastChangeDateTime) {
        return tenantEntityRepository.isChangeOfAttributeChangeLog(getFullClassName(entityType), fieldName, entityId, longToLocalDateTime(lastChangeDateTime));
    }

    @Override
    public PageQueryObject<Object> listValueOfAttributeChangelog(String entityType, String fieldName, Long entityId, Long lastChangeDateTime) {
        return tenantEntityRepository.listValueOfAttributeChangelog(getFullClassName(entityType), fieldName, entityId, longToLocalDateTime(lastChangeDateTime));
    }
}
