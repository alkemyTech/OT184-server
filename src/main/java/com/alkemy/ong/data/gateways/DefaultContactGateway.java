package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.ContactEntity;
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

    public Contact toModel(ContactEntity contactEntity){
        return Contact.builder()
                .id(contactEntity.getId())
                .name(contactEntity.getName())
                .email(contactEntity.getEmail())
                .phone(contactEntity.getEmail())
                .message(contactEntity.getMessage())
                .build();
    }

    public ContactEntity toModel(Contact contact){
        return ContactEntity.builder()
                .id(contact.getId())
                .name(contact.getName())
                .email(contact.getEmail())
                .phone(contact.getEmail())
                .message(contact.getMessage())
                .build();
    }
}
