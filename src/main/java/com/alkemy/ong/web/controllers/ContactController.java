package com.alkemy.ong.web.controllers;

import com.alkemy.ong.domain.contacts.Contact;
import com.alkemy.ong.domain.contacts.ContactService;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContactController {
    private final ContactService contactService;
    public ContactController(ContactService contactService){
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<List<ContactDTO>> findAll() {
        return ResponseEntity.ok().body(toDTO(contactService.findAll()));
    }

    private ContactDTO toDTO(Contact contact) {
        return ContactDTO.builder().build();
    }

    @Data
    @Builder
    private class ContactDTO {

    }
}
