package com.example.controller;

import com.example.model.Contacts;
import com.example.service.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/contacts")
@Validated
public class ContactController {

    @Autowired
    private ContactServiceImpl contactService;

    @GetMapping(value="movie")
    public String giveMovieName() {
        return "ThunderBolt!";
    }

    @PostMapping
    public  Contacts addContacts(@Valid @RequestBody Contacts contacts) {
        return contactService.addContactsData(contacts);

    }

    @GetMapping
    public Contacts getContactRecord() {
        return contactService.retrieveContactData();
    }

    /*@Valid @Pattern(regexp="[(a-zA-Z)]+",message="Enter valid emailId")*/
    @GetMapping(value="/byEmail/{emailId}")
    public Contacts findByEmailId(@PathVariable String emailId) {
        return contactService.retrieveByEmailId(emailId);
    }
}
