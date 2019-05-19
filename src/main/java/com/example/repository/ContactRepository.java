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
    private static final String RETRIEVE_ALL_CONTACTS_BYEMAILID= "select * from contact_records where emailid=?";

    private PreparedStatement createContactRecordStatement;
    private PreparedStatement retrieveAllContacts;
    private PreparedStatement retrieveAllContactsByEmailId;

    private BoundStatement bound;

    @Autowired
    private Session session;

    @PostConstruct
    public void prepareStatements() {
        createContactRecordStatement = session.prepare(CREATE_CONTACT_RECORD_STATEMENT);
        retrieveAllContacts = session.prepare(RETRIEVE_ALL_CONTACTS);
        retrieveAllContactsByEmailId = session.prepare(RETRIEVE_ALL_CONTACTS_BYEMAILID);
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
        bound=retrieveAllContacts.bind();
        ResultSet result=session.execute(bound);

        return executeResultSet(result);

    }

    public Contacts retrieveAllContactsByEmailId(String emailId) {
        bound=retrieveAllContactsByEmailId.bind(emailId);
        ResultSet resultSet=session.execute(bound);

        return executeResultSet(resultSet);
    }

    public Contacts executeResultSet(ResultSet resultSet) {
        List<ContactRecord> contactRecords=new ArrayList<>();
        resultSet.iterator()
            .forEachRemaining(
               row -> contactRecords.add( //TODO- fields are not mapping correctly. Need to look again.
                   new ContactRecord(
                      row.getString("company"),
                      row.getString("emailId"),
                      row.getString("name"),
                      row.getInt("phoneNumber")
                   )));

        Contacts contacts=new Contacts();
        contacts.setContacts(contactRecords);

        return contacts;
    }



}
