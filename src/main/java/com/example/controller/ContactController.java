package com.example.controller;

import com.example.model.ContactRecord;
import com.example.model.Contacts;
import com.example.service.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    //private List<ContactRecord> contactRecords = new ArrayList<>();

    @Autowired
    private ContactServiceImpl contactService;
//
//    @Autowired
//    private RestTemplate restTemplate;

    @GetMapping(value="movie")
    public String giveMovieName() {
        return "ThunderBolt!";
    }

    @PostMapping
    public  Contacts addContacts(@RequestBody Contacts contacts) {
        return contactService.addContactsData(contacts);

//        for(ContactRecord contactRecord: contacts.getContacts()) {
//            contactRecords.add(contactRecord);
//        }
//
//        return contacts;

    }

    @GetMapping
    public Contacts getContactRecord() {
        return contactService.retrieveContactData();
//        Contacts contacts=new Contacts();
//        contacts.setContacts(contactRecords);
//
//        return contacts;

    }
}
