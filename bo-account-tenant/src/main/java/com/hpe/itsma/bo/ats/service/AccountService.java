package com.hpe.itsma.bo.ats.service;

import com.hpe.itsma.bo.ats.service.domain.AccountEntity;
import com.hpe.itsma.bo.common.api.PageQueryEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@PreAuthorize("hasRole('FARM_ADMIN')")
public interface AccountService {

    PageQueryEntity<AccountEntity> getAllAccounts(long limit, long offset, String filter, List<String> orderBy, boolean fetchTotalCount);

    AccountEntity getAccountById(Long accountId);

    AccountEntity createAccounts(AccountEntity accountEntity);

    AccountEntity updateAccount(Long id, AccountEntity anAccount);

    void delete(long id);

    boolean suiteSSOAccountAllowable();

    int getChildenAccountByCustomerId(Long customerId);

    boolean ifExistLiveRecWithSameName(String name, Long id);

}
