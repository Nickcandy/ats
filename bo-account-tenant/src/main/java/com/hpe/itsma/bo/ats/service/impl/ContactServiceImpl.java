package com.hpe.itsma.bo.ats.service.impl;

import com.hpe.itsma.bo.ats.service.ContactService;
import com.hpe.itsma.bo.ats.service.domain.ContactEntity;
import com.hpe.itsma.bo.ats.service.repository.ContactEntityRepository;
import com.hpe.itsma.bo.common.api.PageQueryEntity;
import com.hpe.itsma.bo.common.exception.BoEntityNotFoundException;
import com.hpe.itsma.bo.common.filter.FilterBooleanExpression;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactEntityRepository contactEntityRepository;

    Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);

    @Override
    public PageQueryEntity<ContactEntity> getAllContacts(int limit, int offset, String filter, boolean fetchTotalCount) {
        FilterBooleanExpression<ContactEntity> filterBooleanExpression = new FilterBooleanExpression<>();
        BooleanExpression booleanExpression = filterBooleanExpression.getFilterBooleanExpression(ContactEntity.class, filter);
        return contactEntityRepository.findAll(booleanExpression,limit,offset,fetchTotalCount);
    }

    @Override
    public PageQueryEntity<ContactEntity> getAllContacts(int limit, int offset, String filter, List<String> orderByField, boolean fetchTotalCount) {
        FilterBooleanExpression<ContactEntity> filterBooleanExpression = new FilterBooleanExpression<>();
        BooleanExpression booleanExpression = filterBooleanExpression.getFilterBooleanExpression(ContactEntity.class, filter);
        return  contactEntityRepository.findAll(booleanExpression,limit,offset,orderByField,fetchTotalCount);
    }

    @Override
    public ContactEntity getContactById(Long contactId) {
        ContactEntity contactEntity = contactEntityRepository.findOne(contactId);
        if(contactEntity==null){
            throw new BoEntityNotFoundException(ContactEntity.getEntityType(), String.valueOf(contactId));
        }
        return contactEntity;
    }

    @Override
    public ContactEntity createContacts(ContactEntity anContact) {
        return contactEntityRepository.save(anContact);
    }

    @Override
    public ContactEntity updateContact( ContactEntity anContact) {
        if (!contactEntityRepository.exists(anContact.getId())) {
            throw new BoEntityNotFoundException(ContactEntity.getEntityType(), String.valueOf(anContact.getId()));
        }
        return contactEntityRepository.saveAndFlush(anContact);
    }

    @Override
    public void delete(long id) {
        ContactEntity entity = getContactById(id);
        entity.setDeleted(Boolean.TRUE);
        updateContact(entity);
    }
}
