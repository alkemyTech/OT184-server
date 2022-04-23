package com.alkemy.ong.domain.contacts;

import org.springframework.stereotype.Service;

@Service
public class ContactService {
    private final ContactGateway contactGateway;
    public ContactService(ContactGateway contactGateway){
        this.contactGateway = contactGateway;
    }

    public Contact findAll() {
        return contactGateway.findAll();
    }
}
