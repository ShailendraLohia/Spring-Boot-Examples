package com.example.service;

import com.example.model.Contacts;

public interface ContactService {

    public Contacts addContactsData(Contacts contacts);
    public Contacts retrieveContactData();
}
