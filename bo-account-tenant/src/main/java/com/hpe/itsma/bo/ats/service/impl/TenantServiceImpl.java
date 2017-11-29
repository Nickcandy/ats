package com.hpe.itsma.bo.ats.service.impl;

import com.hpe.itsma.bo.ats.agent.BoLicenseAgent;
import com.hpe.itsma.bo.ats.agent.SawTenantManageAgent;
import com.hpe.itsma.bo.ats.model.deploy.LicenseActivityModel;
import com.hpe.itsma.bo.ats.model.deploy.TenantStateModel;
import com.hpe.itsma.bo.ats.service.TenantService;
import com.hpe.itsma.bo.ats.service.domain.QTenantEntity;
import com.hpe.itsma.bo.ats.service.domain.TenantEntity;
import com.hpe.itsma.bo.ats.service.domain.enumeration.TenantState;
import com.hpe.itsma.bo.ats.service.domain.enumeration.TenantType;
import com.hpe.itsma.bo.ats.service.repository.TenantEntityRepository;
import com.hpe.itsma.bo.ats.util.ATSConstants;
import com.hpe.itsma.bo.common.api.PageQueryEntity;
import com.hpe.itsma.bo.common.api.PageQueryModel;
import com.hpe.itsma.bo.common.exception.BoConstraintViolationException;
import com.hpe.itsma.bo.common.exception.BoEntityNotFoundException;
import com.hpe.itsma.bo.common.exception.BoInvalidParameterException;
import com.hpe.itsma.bo.common.exception.ServiceException;
import com.hpe.itsma.bo.common.filter.FilterBooleanExpression;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by sheyu on 2017/6/11.
 */
@Service
public class TenantServiceImpl implements TenantService {
    private static final long MAX_TENANT_ID = 999999999L;
    private static final long MIN_TENANT_ID = 100000100L;
    public static final String ERROR_SERVER_CANNOT_CHANGE_TENANT_TYPE = "error.server.cannot_change_tenant_type";

    private static List<Long> reservedTenantIds = Collections.singletonList(857561481L); //ops tenant

    @Value("${saw.callback.service.host}")
    private String host;

    @Value("${saw.callback.service.port}")
    private String port;

    @Value("${saw.callback.service.url}")
    private String url;

    @Value("${saw.schema}")
    private String sawSchema;

    @Value("${saw.url.schema:}")
    private String sawUrlSchema;

    @Value("${saw.url.port:}")
    private String sawUrlPort;

    @Value("${saw.cluster-host}")
    private String sawClusterHost;

    @Value("${saw.integration.user.internal.username:}")
    private String sawIntegrationInternalUserName;

    @Value("${saw.integration.user.internal.password:}")
    private String sawIntegrationInternalPassword;

    @Value("${saw.integration.user.external.username:}")
    private String sawIntegrationExternalUserName;

    @Value("${saw.integration.user.external.password:}")
    private String sawIntegrationExternalPassword;

    @Value("${saw.port}")
    private String sawPort;

    @Autowired
    private MessageSource messageSource;


    @Autowired
    private BoLicenseAgent licenseAgent;


    @Autowired
    private SawTenantManageAgent sawTenantManageAgent;

    @Autowired
    private TenantEntityRepository tenantEntityRepository;

    Logger logger = LoggerFactory.getLogger(TenantServiceImpl.class);

    @Override
    public PageQueryEntity<TenantEntity> getAllTenants(int limit, int offset, List<String> orderByFieldList, String filter, boolean fetctTotalCount) {
        FilterBooleanExpression<TenantEntity> filterBooleanExpression = new FilterBooleanExpression<>();
        BooleanExpression booleanExpression = filterBooleanExpression.getFilterBooleanExpression(TenantEntity.class, filter);
        return tenantEntityRepository.findAll(booleanExpression, limit, offset, orderByFieldList, fetctTotalCount);
    }

    @Override
    public TenantEntity getTenantById(Long tenantId) {
        TenantEntity tenantEntity = tenantEntityRepository.findOne(tenantId);
        if (tenantEntity == null) {
            throw new BoEntityNotFoundException(TenantEntity.getEntityType(), String.valueOf(tenantId));
        }
        sawUrlSchema = sawUrlSchema == null ? "https:" : sawUrlSchema;
        tenantEntity.setUrl(sawUrlSchema + "://" + sawClusterHost + (sawUrlPort == null ? "" : (":" + sawUrlPort)) + "/saw/ess?TENANTID=" + tenantId);
        return tenantEntity;
    }

    @Override
    public TenantEntity createTenants(TenantEntity aTenant) {
        if (ifExistLiveRecWithSameName(aTenant.getName(), -1L)) {
            throw new BoConstraintViolationException(ATSConstants.ERROT_TENANT_NAME_UNIQUE, messageSource.getMessage(ATSConstants.ERROT_TENANT_NAME_UNIQUE, new String[]{aTenant.getName()}, null), aTenant.getName());
        }
        Long randomId = generateRandomTenantId();
        aTenant.setId(randomId);
        return tenantEntityRepository.save(aTenant);
    }

    @Override
    public TenantEntity updateTenant(Long id, TenantEntity aTenant) {
        if (!id.equals(aTenant.getId())) {
            throw new BoInvalidParameterException(Arrays.asList("id in url", "id in payload").toString(), Arrays.asList(id, aTenant.getId()).toString());
        }
        if (!tenantEntityRepository.exists(id)) {
            logger.error("Entity {{}} not found with id: {}", TenantEntity.class, id);
            throw new BoEntityNotFoundException(TenantEntity.getEntityType(), String.valueOf(id));
        }
        if (ifExistLiveRecWithSameName(aTenant.getName(), aTenant.getId())) {
            throw new BoConstraintViolationException(ATSConstants.ERROT_TENANT_NAME_UNIQUE, messageSource.getMessage(ATSConstants.ERROT_TENANT_NAME_UNIQUE, new String[]{aTenant.getName()}, null), aTenant.getName());
        }
        TenantEntity tenantEntity = this.getTenantById(id);
        validatorUpdateTenant(tenantEntity, aTenant);
        aTenant.setLastUpdatedAt(tenantEntityRepository.getLastActiveDate(aTenant.getId()));
        return tenantEntityRepository.saveAndFlush(aTenant);
    }

    private static void validatorUpdateTenant(TenantEntity tenantEntity, TenantEntity tenantEntityChange) {
        validatorUpdateTenantState(tenantEntity.getTenantState(), tenantEntity.getTenantState());
        if (!TenantState.NEW.equals(tenantEntity.getTenantState())) {
            validatorUpdateTenantType(tenantEntity.getTenantType(), tenantEntityChange.getTenantType());
        }
    }

    private static void validatorUpdateTenantState(TenantState tenantState, TenantState tenantStateChange) {
        if (tenantState != tenantStateChange) {
            boolean canNotChange = (TenantState.REMOVED.equals(tenantState)) || (TenantState.NEW.equals(tenantState) && !TenantState.REMOVED.equals(tenantStateChange)) ||
                    (TenantState.PENDING_FOR_REMOVAL.equals(tenantState) && !TenantState.REMOVED.equals(tenantStateChange)) ||
                    (TenantState.ACTIVE.equals(tenantState) && !TenantState.INACTIVE.equals(tenantStateChange)) ||
                    (TenantState.INACTIVE.equals(tenantState) && (!TenantState.ACTIVE.equals(tenantStateChange) && !TenantState.REMOVED.equals(tenantStateChange)));
            if (canNotChange) {
                throw new BoInvalidParameterException(Arrays.asList("entity type", "status").toString(), Arrays.asList(TenantEntity.getEntityType(), String.valueOf(tenantStateChange)).toString());
            }
        }
    }

    private static void validatorUpdateTenantType(TenantType tenantType, TenantType tenantTypeChange) {
        if (!tenantType.equals(tenantTypeChange)) {
            throw new BoInvalidParameterException(Arrays.asList("entity type", "tenant type").toString(), Arrays.asList(TenantEntity.getEntityType(), String.valueOf(tenantTypeChange)).toString());
        }
    }

    @Override
    public PageQueryEntity<TenantEntity> searchTenant(int limit, int offset, List<String> orderByFieldList, String keyWords, String filter, boolean fetchTotalCount) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.or(QTenantEntity.tenantEntity.description.contains(keyWords))
                .or(QTenantEntity.tenantEntity.displayLabel.contains(keyWords))
                .or(QTenantEntity.tenantEntity.name.contains(keyWords));
        FilterBooleanExpression<TenantEntity> filterBooleanExpression = new FilterBooleanExpression<>();
        BooleanExpression booleanExpression = filterBooleanExpression.getFilterBooleanExpression(TenantEntity.class, filter);
        booleanBuilder.and(booleanExpression);
        return tenantEntityRepository.findAll(booleanBuilder, limit, offset, orderByFieldList, fetchTotalCount);
    }

    @Override
    public PageQueryEntity<TenantEntity> getAllPublicTenantsInAccountOrId(List<Long> accountId, List<Long> tenantIds, String filter, boolean fetchTotalCount) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(accountId == null ? Expressions.ONE.eq(1) : QTenantEntity.tenantEntity.account.in(accountId));
        booleanBuilder.and(QTenantEntity.tenantEntity.accessControl.eq(true));
        if (tenantIds != null) booleanBuilder.or(QTenantEntity.tenantEntity.id.in(tenantIds));
        FilterBooleanExpression<TenantEntity> filterBooleanExpression = new FilterBooleanExpression<>();
        BooleanExpression booleanExpression = filterBooleanExpression.getFilterBooleanExpression(TenantEntity.class, filter);
        booleanBuilder.and(booleanExpression);
        return tenantEntityRepository.findAll(booleanBuilder, fetchTotalCount);
    }

    @Override
    public void delete(long id) {
        TenantEntity entity = getTenantById(id);
        if (entity != null && entity.getTenantState() != null && entity.getTenantState().equals(TenantState.ACTIVE)) {
            throw new BoConstraintViolationException("error.server.cannot_delete_active_tenant", messageSource.getMessage("error.server.cannot_delete_active_tenant", null, null));
        }
        if (entity != null) {
            entity.setDeleted(Boolean.TRUE);
            entity.setTenantState(TenantState.REMOVED);
            updateTenant(id, entity);
        }
    }

    @Override
    public int getChildenTenantsByAccountId(Long accoundId) {
        return tenantEntityRepository.getChildenTenantsByAccountId(accoundId);
    }


    private Long generateRandomTenantId() {
        Long randomId = (long) (Math.random() * MAX_TENANT_ID);
        List allTenantIds = tenantEntityRepository.getAllTenantIds();
        while (allTenantIds.contains(randomId) || randomId < MIN_TENANT_ID || reservedTenantIds.contains(randomId)) {
            randomId = (long) (Math.random() * MAX_TENANT_ID);
        }
        return randomId;
    }

    @Override
    public boolean ifExistLiveRecWithSameName(String name, Long id) {
        return tenantEntityRepository.countOfRecWithSameName(name, id) > 0;
    }

    @Override
    public Boolean changeTenantState(TenantStateModel model) {
        TenantState newState = model.getTenantState();
        TenantEntity tenantEntity = this.getTenantById(model.getTenantId());
        TenantState oldState = tenantEntity.getTenantState();
        if ( (TenantState.ACTIVE.equals(oldState) && TenantState.INACTIVE.equals(newState)) ||
                (TenantState.INACTIVE.equals(oldState) && TenantState.ACTIVE.equals(newState)) ) {
            tenantEntityRepository.updateTenantState(model.getTenantId(), newState);
            return true;
        }
        return false;
    }

    @Override
    public void changeTenantType(Long tenantId, TenantType tenantType) {
        TenantEntity tenantEntity = this.getTenantById(tenantId);
        if(TenantState.NEW.equals(tenantEntity.getTenantState())) {
            throw new ServiceException(ERROR_SERVER_CANNOT_CHANGE_TENANT_TYPE,Collections.EMPTY_LIST,"can not change tenant type");
        }

        PageQueryModel<LicenseActivityModel> licenses = licenseAgent.getLicensesByTenantId(tenantId);
        if(licenses.getTotalCount() > 0) {
            throw new ServiceException(ERROR_SERVER_CANNOT_CHANGE_TENANT_TYPE,Collections.EMPTY_LIST,"can not change tenant type");
        }

        if(tenantType.equals(tenantEntity.getTenantType())) {
            return;
        }

        sawTenantManageAgent.changeTenantType(tenantId, tenantType);

        tenantEntity.setTenantType(tenantType);
        tenantEntityRepository.saveAndFlush(tenantEntity);
    }
}
