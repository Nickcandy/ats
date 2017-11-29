package com.hpe.itsma.bo.ats.service;

import com.hpe.itsma.bo.ats.service.domain.ContactEntity;
import com.hpe.itsma.bo.common.api.PageQueryEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@PreAuthorize("hasRole('FARM_ADMIN')")
public interface ContactService {

    PageQueryEntity<ContactEntity> getAllContacts(int limit, int offset, String filter, boolean fetchTotalCount);

    PageQueryEntity<ContactEntity> getAllContacts(int limit, int offset, String filter, List<String> orderByField, boolean fetchTotalCount);

    ContactEntity getContactById(Long contactId);

    ContactEntity createContacts(ContactEntity contactEntity);

    ContactEntity updateContact(ContactEntity anContact);

    void delete(long id);
}
