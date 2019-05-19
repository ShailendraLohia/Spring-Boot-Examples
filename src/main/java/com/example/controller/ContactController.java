package com.example.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

import com.example.model.Contacts;
import com.example.service.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/contacts")
@Validated
@EnableBinding(Source.class)
public class ContactController {

    @Autowired
    private ContactServiceImpl contactService;

    @Autowired
    @Qualifier("output")
    private MessageChannel messageChannel;


    @GetMapping(value="movie")
    public String giveMovieName() {
        return "ThunderBolt!";
    }

    @PostMapping
    public  ResponseEntity<Contacts> addContacts(@Valid @RequestBody Contacts contacts) {

        return new ResponseEntity<>(
                contactService.addContactsData(contacts),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Contacts> getContactRecord() {

        return new ResponseEntity<>(
                contactService.retrieveContactData(),HttpStatus.CREATED);
    }

    /*@Valid @Pattern(regexp="[(a-zA-Z)]+",message="Enter valid emailId")*/
    @GetMapping(value="/byEmail/{emailId}")
    public ResponseEntity<Contacts> findByEmailId(@PathVariable String emailId) {

        return new ResponseEntity<>(
                contactService.retrieveByEmailId(emailId),HttpStatus.CREATED);
    }

    @PostMapping("/my-contacts")

    public ResponseEntity<String> myNewContacts(@RequestBody Contacts contacts) {
        messageChannel.send(new GenericMessage<>(contacts.toString()));

        return new ResponseEntity<>("Message added successfully",HttpStatus.CREATED);
    }




}
