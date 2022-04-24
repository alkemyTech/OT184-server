package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.repositories.ContactRepository;
import com.alkemy.ong.domain.contacts.Contact;
import com.alkemy.ong.domain.contacts.ContactGateway;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultContactGateway implements ContactGateway {
    private final ContactRepository contactRepository;

    public DefaultContactGateway(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Contact> findAll() {
        contactRepository.findAll();
        return null;
    }


}
