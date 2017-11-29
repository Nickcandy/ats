package com.hpe.itsma.bo.ats.service.impl;

import com.hpe.itsma.bo.ats.agent.BoUserAgent;
import com.hpe.itsma.bo.ats.service.AccountService;
import com.hpe.itsma.bo.ats.service.TenantService;
import com.hpe.itsma.bo.ats.service.domain.AccountEntity;
import com.hpe.itsma.bo.ats.service.repository.AccountEntityRepository;
import com.hpe.itsma.bo.ats.util.ATSConstants;
import com.hpe.itsma.bo.common.api.PageQueryEntity;
import com.hpe.itsma.bo.common.exception.BoConstraintViolationException;
import com.hpe.itsma.bo.common.exception.BoEntityNotFoundException;
import com.hpe.itsma.bo.common.exception.BoInvalidParameterException;
import com.hpe.itsma.bo.common.filter.FilterBooleanExpression;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private static final long MAX_ACCOUNT_ID = 999999999L;
    private static final long MIN_ACCOUNT_ID = 100000100L;
    private static List<Long> reservedAccountIds = Collections.singletonList(10000L); //10000= sys account


    @Autowired
    private MessageSource messageSource;

    @Value("${itsma.suiteSSOEnabled}")
    private boolean suiteSSOEnabled;

    @Autowired
    private AccountEntityRepository accountEntityRepository;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private BoUserAgent userAgent;

    @Override
    public PageQueryEntity<AccountEntity> getAllAccounts(long limit, long offset, String filter, List<String> orderBy, boolean fetchTotalCount) {
        FilterBooleanExpression<AccountEntity> filterBooleanExpression = new FilterBooleanExpression<>();
        BooleanExpression booleanExpression = filterBooleanExpression.getFilterBooleanExpression(AccountEntity.class, filter);
        return accountEntityRepository.findAll(booleanExpression, (int) limit, (int) offset, orderBy, fetchTotalCount);
    }

    @Override
    public AccountEntity getAccountById(Long accountId) {
        AccountEntity accountEntity = accountEntityRepository.findOne(accountId);
        if (accountEntity == null) {
            throw new BoEntityNotFoundException(AccountEntity.getEntityType(), String.valueOf(accountId));
        }
        return accountEntity;
    }

    @Override
    public AccountEntity createAccounts(AccountEntity anAccount) {
        if (ifExistLiveRecWithSameName(anAccount.getName(), -1L)) {
            throw new BoConstraintViolationException(ATSConstants.ERROR_ACCOUNT_NAME_UNIQUE, messageSource.getMessage(ATSConstants.ERROR_ACCOUNT_NAME_UNIQUE, new String[]{anAccount.getName()}, null), anAccount.getName());
        }
        Long randomId = generateUniqueRandomAccountId();
        anAccount.setId(randomId);
        return accountEntityRepository.save(anAccount);
    }

    @Override
    public AccountEntity updateAccount(Long id, AccountEntity anAccount) {
        if (!id.equals(anAccount.getId())) {
            throw new BoInvalidParameterException(Arrays.asList("id in url", "id in payload").toString(), Arrays.asList(id, anAccount.getId()).toString());
        }
        if (!accountEntityRepository.exists(id)) {
            throw new BoEntityNotFoundException(AccountEntity.getEntityType(), String.valueOf(id));
        }
        if (ifExistLiveRecWithSameName(anAccount.getName(), anAccount.getId())) {
            throw new BoConstraintViolationException(ATSConstants.ERROR_ACCOUNT_NAME_UNIQUE, messageSource.getMessage(ATSConstants.ERROR_ACCOUNT_NAME_UNIQUE, new String[]{anAccount.getName()}, null), anAccount.getName());
        }
        return accountEntityRepository.saveAndFlush(anAccount);
    }

    @Override
    public void delete(long id) {
        int childTenantsCount = tenantService.getChildenTenantsByAccountId(id);
        if (childTenantsCount > 0) {
            throw new BoConstraintViolationException(ATSConstants.ERROR_ACCOUNT_TENANT_DELETE, messageSource.getMessage(ATSConstants.ERROR_ACCOUNT_TENANT_DELETE, new String[]{String.valueOf(childTenantsCount)}, null), String.valueOf(childTenantsCount));
        }
        long childUserCount = userAgent.listUserByAccountId(id).getTotalCount();
        if (childUserCount > 0) {
            throw new BoConstraintViolationException(ATSConstants.ERROR_ACCOUNT_USER_DELETE, messageSource.getMessage(ATSConstants.ERROR_ACCOUNT_USER_DELETE, new String[]{String.valueOf(childUserCount)}, null), String.valueOf(childUserCount));
        }
        AccountEntity entity = getAccountById(id);
        entity.setDeleted(Boolean.TRUE);
        updateAccount(id, entity);
    }

    @Override
    public boolean suiteSSOAccountAllowable() {
        if (!suiteSSOEnabled) {
            return false;
        } else {
            if (accountEntityRepository.getSSOEnabledAccounts() > 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getChildenAccountByCustomerId(Long customerId) {
        return accountEntityRepository.getChildenAccountByCustomerId(customerId);
    }

    @Override
    public boolean ifExistLiveRecWithSameName(String name, Long id) {
        return accountEntityRepository.countOfRecWithSameName(name, id) > 0;
    }

    private Long generateUniqueRandomAccountId() {
        Long randomId = (long) (Math.random() * MAX_ACCOUNT_ID);
        List allAccountIds = accountEntityRepository.getAllAccountIds();
        while (allAccountIds.contains(randomId) || randomId < MIN_ACCOUNT_ID || reservedAccountIds.contains(randomId)) {
            randomId = (long) (Math.random() * MAX_ACCOUNT_ID);
        }
        return randomId;
    }
}
