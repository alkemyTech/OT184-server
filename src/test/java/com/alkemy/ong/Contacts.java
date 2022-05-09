package com.alkemy.ong;

import com.alkemy.ong.data.entities.ContactEntity;
import com.alkemy.ong.data.repositories.ContactRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class Contacts {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContactRepository contactRepo;

    private ContactEntity contactEntity = ContactEntity.builder()
            .id(1L)
            .name("contact")
            .email("contact@alkemy.com")
            .phone("1144054522")
            .message("tocayo")
            .build();

}