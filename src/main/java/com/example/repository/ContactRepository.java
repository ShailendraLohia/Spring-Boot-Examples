package com.example.repository;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.example.model.ContactRecord;
import com.example.model.Contacts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContactRepository {
    private static final String CREATE_CONTACT_RECORD_STATEMENT="INSERT INTO contact_records (emailId, name, company,phoneNumber) values (?, ?, ?, ?) using TTL ?";
    private static final String RETRIEVE_ALL_CONTACTS="select * from contact_records";

    private PreparedStatement createContactRecordStatement;
    private PreparedStatement retrieveAllContacts;

    private BoundStatement bound;

    @Autowired
    private Session session;

    @PostConstruct
    public void prepareStatements() {
        createContactRecordStatement = session.prepare(CREATE_CONTACT_RECORD_STATEMENT);
        retrieveAllContacts = session.prepare(RETRIEVE_ALL_CONTACTS);
    }

    public void createContacts(Contacts contacts) {
        for(ContactRecord contactRecord: contacts.getContacts()) {
            bound=createContactRecordStatement.bind(contactRecord.getEmailId(),
                    contactRecord.getName(),contactRecord.getCompany(),
                    contactRecord.getPhoneNumber(),600);
            session.execute(bound);
        }
    }

    public Contacts retrieveAllContacts() {
        List<ContactRecord> contactRecords = new ArrayList<>();
        bound=retrieveAllContacts.bind();
        ResultSet result=session.execute(bound);

        result.iterator().forEachRemaining(row ->
                contactRecords.add(new ContactRecord(
                        row.getString("emailId"),
                        row.getString("name"),
                        row.getString("company"),
                        row.getInt("phoneNumber")
                )));

        Contacts contacts=new Contacts();
        contacts.setContacts(contactRecords);

        return contacts;

    }



}
