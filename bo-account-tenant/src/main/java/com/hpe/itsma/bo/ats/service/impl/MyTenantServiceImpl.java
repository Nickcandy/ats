package com.hpe.itsma.bo.ats.service.impl;

import com.hpe.itsma.bo.ats.service.MyTenantService;
import com.hpe.itsma.bo.ats.service.domain.TenantEntity;
import com.hpe.itsma.bo.ats.service.repository.TenantEntityRepository;
import com.hpe.itsma.bo.common.api.PageQueryEntity;
import com.hpe.itsma.bo.common.security.UserDetailsImpl;
import com.hpe.itsma.bo.common.security.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sheyu on 2017/6/11.
 */
@Service
public class MyTenantServiceImpl implements MyTenantService {

    @Value("${saw.schema}")
    private String sawSchema;

    @Value("${saw.url.schema:}")
    private String sawUrlSchema;

    @Value("${saw.url.port:}")
    private String sawUrlPort;

    @Value("${saw.cluster-host}")
    private String sawClusterHost;

    private Logger logger = LoggerFactory.getLogger(MyTenantServiceImpl.class);

    @Autowired
    private TenantEntityRepository tenantEntityRepository;

    @Override
    public PageQueryEntity<TenantEntity> getMyTenants() {
        PageQueryEntity<TenantEntity> ret = new PageQueryEntity<>();
        List<TenantEntity> tenants = new ArrayList<>();
        List<String> tenantIdStrList = getTenantIdsFromContext();
        if (tenantIdStrList != null && !tenantIdStrList.isEmpty()) {
            List<Long> tenantIdLongList = getTenantIdListWithTypeLong(tenantIdStrList);
            tenants = tenantEntityRepository.getMyTenants(tenantIdLongList);
            setTenantURL(tenants);
        }
        ret.setEntities(tenants);
        return ret;
    }

    private void setTenantURL(List<TenantEntity> tenants) {
        sawUrlSchema = sawUrlSchema == null ? "https:" : sawUrlSchema;
        for(TenantEntity tenantEntity: tenants) {
            tenantEntity.setUrl(sawUrlSchema + "://" + sawClusterHost + (sawUrlPort == null ? "" : (":" + sawUrlPort)) + "/saw/ess?TENANTID=" + tenantEntity.getId());
        }
    }

    private List<String> getTenantIdsFromContext() {
        List<String> ret = null;
        SecurityContext context = SecurityContextHolder.getContext();
        if (context != null && context.getAuthentication() != null) {
            Object userDetails = context.getAuthentication().getDetails();
            if (userDetails != null && userDetails instanceof UserDetailsImpl) {
                UserProfile userProfile = ((UserDetailsImpl) userDetails).getUser();
                if (userProfile != null && userProfile.getId() != null) {
                    ret = userProfile.getTenants();
                }
            }
        }
        return ret;
    }

    private List<Long> getTenantIdListWithTypeLong(List<String> tenantIdStrList) {
        List<Long> tenantIdLongList = new ArrayList<>();
        for (String tenantIdStr : tenantIdStrList) {
            try {
                tenantIdLongList.add(Long.parseLong(tenantIdStr));
            } catch (NumberFormatException nfe) {
                logger.error("invalid tenantId: " + tenantIdStr);
            }
        }
        return tenantIdLongList;
    }
}
