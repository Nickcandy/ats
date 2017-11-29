package com.hpe.itsma.bo.ats.service.impl;

import com.hpe.itsma.bo.ats.service.AccountService;
import com.hpe.itsma.bo.ats.service.CustomerService;
import com.hpe.itsma.bo.ats.service.domain.CustomerEntity;
import com.hpe.itsma.bo.ats.service.repository.CustomerEntityRepository;
import com.hpe.itsma.bo.common.api.PageQueryEntity;
import com.hpe.itsma.bo.common.exception.BoConstraintViolationException;
import com.hpe.itsma.bo.common.exception.BoEntityNotFoundException;
import com.hpe.itsma.bo.common.filter.FilterBooleanExpression;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CustomerEntityRepository customerEntityRepository;

    @Autowired
    private AccountService accountService;

    Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Override
    public PageQueryEntity<CustomerEntity> getAllCustomers(int limit, int offset, String filter, boolean fetchTotalCount) {
        FilterBooleanExpression<CustomerEntity> filterBooleanExpression = new FilterBooleanExpression<>();
        BooleanExpression booleanExpression = filterBooleanExpression.getFilterBooleanExpression(CustomerEntity.class, filter);
        return customerEntityRepository.findAll(booleanExpression,limit,offset,fetchTotalCount);
    }

    @Override
    public PageQueryEntity<CustomerEntity> getAllCustomers(int limit, int offset, String filter, List<String> orderByField, boolean fetchTotalCount) {
        FilterBooleanExpression<CustomerEntity> filterBooleanExpression = new FilterBooleanExpression<>();
        BooleanExpression booleanExpression = filterBooleanExpression.getFilterBooleanExpression(CustomerEntity.class, filter);
        return  customerEntityRepository.findAll(booleanExpression,limit,offset,orderByField,fetchTotalCount);
    }

    @Override
    public CustomerEntity getCustomerById(Long customerId) {
        CustomerEntity customerEntity = customerEntityRepository.findOne(customerId);
        if(customerEntity==null){
            throw new BoEntityNotFoundException(CustomerEntity.getEntityType(), String.valueOf(customerId));
        }
        return customerEntity;
    }

    @Override
    public CustomerEntity createCustomers(CustomerEntity aCustomer) {
        validateIfExistDuplicateNames(aCustomer.getShortName(), aCustomer.getFullName(), -1L);
        return customerEntityRepository.save(aCustomer);
    }

    @Override
    public CustomerEntity updateCustomer( CustomerEntity aCustomer) {
        if (!customerEntityRepository.exists(aCustomer.getId())) {
            throw new BoEntityNotFoundException(CustomerEntity.getEntityType(), String.valueOf(aCustomer.getId()));
        }
        validateIfExistDuplicateNames(aCustomer.getShortName(), aCustomer.getFullName(), aCustomer.getId());
        return customerEntityRepository.saveAndFlush(aCustomer);
    }

    @Override
    public void delete(long id) {
        int childAccountCount = accountService.getChildenAccountByCustomerId(id);
        if(childAccountCount>0) {
            throw new BoConstraintViolationException("error.server.cannot_delete_customer_in_using",messageSource.getMessage("error.server.cannot_delete_customer_in_using",new String[]{String.valueOf(childAccountCount)},null), String.valueOf(childAccountCount));
        }
        CustomerEntity entity = getCustomerById(id);
        entity.setDeleted(Boolean.TRUE);
        updateCustomer(entity);
    }

    @Override
    public boolean validateIfExistDuplicateNames(String shortName, String fullName, Long id) {
        List<CustomerEntity> duplicates = customerEntityRepository.getCustomersWithDuplicateName(shortName, fullName, id);
        if(duplicates!=null && !duplicates.isEmpty()) {
            for(CustomerEntity customerEntity:duplicates) {
                if(customerEntity.getShortName()!=null && shortName!=null && customerEntity.getShortName().equalsIgnoreCase(shortName)) {
                    throw new BoConstraintViolationException("error.server.customer_shortname_not_unique", messageSource.getMessage("error.server.customer_shortname_not_unique",new String[]{shortName},null), shortName);
                }
                if(customerEntity.getFullName()!=null && fullName!=null && customerEntity.getFullName().equalsIgnoreCase(fullName)) {
                    throw new BoConstraintViolationException("error.server.customer_fullname_not_unique", messageSource.getMessage("error.server.customer_fullname_not_unique",new String[]{fullName},null), fullName);
                }
            }
        }
        return true;
    }

}
