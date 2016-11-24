package br.com.kproj.salesman.accounts.contacts.infrastructure.persistence;

import br.com.kproj.salesman.accounts.contacts.domain.model.contact.Contact;
import br.com.kproj.salesman.accounts.contacts.domain.model.contact.ContactRepository;
import br.com.kproj.salesman.accounts.contacts.infrastructure.persistence.springdata.ContactEntityRepositorySpringData;
import br.com.kproj.salesman.accounts.contacts.infrastructure.persistence.translate.ContactEntityToContactConverter;
import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import br.com.kproj.salesman.infrastructure.repository.BaseRespositoryImpl;
import br.com.kproj.salesman.infrastructure.repository.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepositoryHibernate extends BaseRespositoryImpl<Contact, ContactEntity> implements ContactRepository {

    private ContactEntityRepositorySpringData repository;

    private ContactEntityToContactConverter converter;

    @Autowired
    public ContactRepositoryHibernate(ContactEntityRepositorySpringData repository, ContactEntityToContactConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }


    @Override
    public BaseRepositoryLegacy<ContactEntity, Long> getRepository() {
        return repository;
    }

    @Override
    public Converter<ContactEntity, Contact> getConverter() {
        return converter;
    }
}
