package com.example.service;

import com.example.model.ContactRecord;
import com.example.model.Contacts;
import com.example.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    private List<ContactRecord> contactRecords = new ArrayList<>();

    @Autowired
    private ContactRepository contactRepository;

    public Contacts addContactsData(Contacts contacts) {
//        for(ContactRecord contactRecord: contacts.getContacts()) {
//            contactRecords.add(contactRecord);
//        }
        contactRepository.createContacts(contacts);
        return contacts;
    }
    public Contacts retrieveContactData() {
//        Contacts contacts=new Contacts();
//        contacts.setContacts(contactRecords);
//
//        return contacts;

        return contactRepository.retrieveAllContacts();
    }

}
