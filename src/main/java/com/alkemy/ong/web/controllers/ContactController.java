package com.alkemy.ong.web.controllers;

import com.alkemy.ong.domain.contacts.ContactService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {
    private final ContactService contactService;
    public ContactController(ContactService contactService){
        this.contactService = contactService;
    }

}
