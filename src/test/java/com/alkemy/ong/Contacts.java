package com.alkemy.ong;

import com.alkemy.ong.data.entities.ContactEntity;
import com.alkemy.ong.data.repositories.ContactRepository;
import io.swagger.v3.core.util.Json;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class Contacts {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactRepository contactRepo;

    private ContactEntity contactEntity = ContactEntity.builder()
            .id(1L)
            .name("contact")
            .email("contact@alkemy.com")
            .phone("1144054522")
            .message("tocayo")
            .build();

    @Test
    @WithMockUser(authorities  = "ADMIN")
    @DisplayName("Save contact by ADMIN, success case")
    public void saveByAdminSuccess() throws Exception{
        when(contactRepo.save(any(ContactEntity.class))).thenReturn(contactEntity);

        mockMvc.perform(post("/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Json.mapper().writeValueAsString(contactEntity)))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("contact")))
                .andExpect(jsonPath("$.email", is("contact@alkemy.com")))
                .andExpect(jsonPath("$.phone", is("1144054522")))
                .andExpect(jsonPath("$.message", is("tocayo")))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(authorities  = "ADMIN")
    @DisplayName("Save contact by ADMIN, error case")
    public void saveByAdminError() throws Exception{
        contactEntity.setName(null);
        mockMvc.perform(post("/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Json.mapper().writeValueAsString(contactEntity)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities  = "USER")
    @DisplayName("Save contact by USER, error case")
    public void saveByUserError() throws Exception{
        contactEntity.setName(null);
        mockMvc.perform(post("/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Json.mapper().writeValueAsString(contactEntity)))
                .andExpect(status().isForbidden());
    }


}