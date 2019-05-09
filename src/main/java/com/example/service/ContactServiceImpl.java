package com.example.service;

import com.example.model.Contacts;
import com.example.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contacts addContactsData(Contacts contacts) {
        contactRepository.createContacts(contacts);
        return contacts;
    }

    public Contacts retrieveContactData() {
        return contactRepository.retrieveAllContacts();
    }

    public Contacts retrieveByEmailId(String emailId) {
        return contactRepository.retrieveAllContactsByEmailId(emailId);
    }

}
