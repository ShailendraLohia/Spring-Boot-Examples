package com.example.controller;

import com.example.model.Contacts;
import com.example.model.Error;
import com.example.service.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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


}
