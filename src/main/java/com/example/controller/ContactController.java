package com.example.controller;

import com.example.model.Contacts;
import com.example.service.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactServiceImpl contactService;

    @GetMapping(value="movie")
    public String giveMovieName() {
        return "ThunderBolt!";
    }

    @PostMapping
    public  Contacts addContacts(@RequestBody Contacts contacts) {
        return contactService.addContactsData(contacts);
    }

    @GetMapping
    public Contacts getContactRecord() {
        return contactService.retrieveContactData();
    }

    @GetMapping(value="/byEmail/{emailId}")
    public Contacts findByEmailId(@PathVariable String emailId) {
        return contactService.retrieveByEmailId(emailId);
    }
}
