package com.alkemy.ong.domain.contacts;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    private final ContactGateway contactGateway;
    public ContactService(ContactGateway contactGateway){
        this.contactGateway = contactGateway;
    }

    public List<Contact> findAll() {
        return contactGateway.findAll();
    }

    public Contact save(Contact contact) {
        return contactGateway.save(contact);
    }
}
