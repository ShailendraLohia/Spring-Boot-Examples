package com.example.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Table(name="contact_records", keyspace = "test")
public class ContactRecord {

    @PartitionKey
    @Column
    @NotEmpty
    @NotNull
    private String emailId;

    @Column
    @Pattern(regexp="[(a-zA-Z0-9)]+")
    private String name;

    @Column
    private String company;

    @Column
    private int phoneNumber;

    public ContactRecord(String name, String company, String emailId, int phoneNumber) {
        this.name = name;
        this.company = company;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
    }
    public ContactRecord() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
