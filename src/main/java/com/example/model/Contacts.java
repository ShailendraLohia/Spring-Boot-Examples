package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contacts {


    private List<ContactRecord> contacts;


    /*public Contacts(List<ContactRecord> contacts) {
        this.contacts = contacts;
    }*/

//    public List<ContactRecord> getContacts() {
//        return contacts;
//    }
//
//    public void setContacts(List<ContactRecord> contacts) {
//        this.contacts = contacts;
//    }


}
