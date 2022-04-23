package com.alkemy.ong.domain.contacts;

import org.springframework.stereotype.Service;

@Service
public class ContactService {
    ContactGateway contactGateway;
    public ContactService(ContactGateway contactGateway){
        this.contactGateway = contactGateway;
    }
}
