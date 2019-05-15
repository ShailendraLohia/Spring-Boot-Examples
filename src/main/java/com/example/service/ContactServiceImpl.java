package com.example.service;

import com.example.exceptions.ContactNotFoundException;
import com.example.model.Contacts;
import com.example.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
@CacheConfig(cacheNames={"contacts"}) // tells Spring where to store
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contacts addContactsData(Contacts contacts) {
        contactRepository.createContacts(contacts);
        return contacts;
    }

    @CachePut // caches the result of findAll() method
    public Contacts retrieveContactData() {
        Contacts contacts= contactRepository.retrieveAllContacts();

        if(contacts.getContacts().size()==0) {
            throw new ContactNotFoundException("Requested contact Info not found");
        }

        return contacts;
    }

    @Cacheable // caches the result of findAll() method
    public Contacts retrieveByEmailId(String emailId) {

        Contacts contacts= contactRepository.retrieveAllContactsByEmailId(emailId);

        if(contacts.getContacts().size()==0) {
            throw new ContactNotFoundException("Requested contact Info not found");
        }

        return contacts;

    }

}
