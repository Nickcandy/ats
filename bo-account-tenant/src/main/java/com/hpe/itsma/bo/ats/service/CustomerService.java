package com.hpe.itsma.bo.ats.service;

import com.hpe.itsma.bo.ats.service.domain.CustomerEntity;
import com.hpe.itsma.bo.common.api.PageQueryEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@PreAuthorize("hasRole('FARM_ADMIN')")
public interface CustomerService {

    PageQueryEntity<CustomerEntity> getAllCustomers(int limit, int offset, String filter,boolean fetchTotalCount);

    PageQueryEntity<CustomerEntity> getAllCustomers(int limit, int offset, String filter,List<String> orderByField,boolean fetchTotalCount);

    CustomerEntity getCustomerById(Long customerId);

    CustomerEntity createCustomers(CustomerEntity customerEntity);

    CustomerEntity updateCustomer(CustomerEntity anCustomer);

    void delete(long id);

    boolean validateIfExistDuplicateNames(String name, String fullName, Long id);
}
