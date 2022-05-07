package com.alkemy.ong;

import com.alkemy.ong.data.entities.RoleEntity;
import com.alkemy.ong.data.entities.UserEntity;
import com.alkemy.ong.data.repositories.UserRepository;
import com.alkemy.ong.domain.roles.Role;
import com.alkemy.ong.web.controllers.UserController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.alkemy.ong.web.controllers.UserController.*;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@DisplayName("Given a user controller")
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserRepository mockUserRepository;

    @Test
    @WithMockUser(authorities = {"admin", "2"}, username = "admin", password = "123")
    @DisplayName("Should return the requested user if the request has the ADMIN authority")
    public void getUserByIdSuccess() throws Exception {
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .email("user@mail.com")
                .role(RoleEntity.builder().id(1L).name("user").description("user level access").build())
                .build();

        when(mockUserRepository.findById(eq(1L))).thenReturn(Optional.of(userEntity));
        when(mockUserRepository.findByEmail(eq("user"))).thenReturn(userEntity);

        mockMvc.perform(get("/users/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Is.is(1)))
                .andExpect(jsonPath("$.email", Is.is("user@mail.com")))
                .andExpect(jsonPath("$.role.name", Is.is("user")))
                .andExpect(jsonPath("$.role.id", Is.is(1)));
    }

    @Test
    @WithMockUser(authorities = {"user", "2"}, username = "user@mail.com", password = "123")
    @DisplayName("Should return the authenticated user if requested by a non admin user")
    public void getUserByIdSuccessUser() throws Exception {
        String userEmail = "user@mail.com";
        UserEntity userEntity = UserEntity.builder()
                .id(2L)
                .email(userEmail)
                .role(RoleEntity.builder().id(1L).name("USER").description("User level access").build())
                .build();

        when(mockUserRepository.findByEmail(eq(userEmail))).thenReturn(userEntity);

        mockMvc.perform(get("/users/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.email", Is.is(userEmail)))
                .andExpect(jsonPath("$.id", Is.is(2)))
                .andExpect(jsonPath("$.role.name", Is.is("USER")))
                .andExpect(jsonPath("$.role.id", Is.is(1)));
    }

    @Test
    @WithMockUser(authorities = {"admin", "2"}, username = "admin@mail.com", password = "123")
    @DisplayName("Sound return not found when requesting a non existing user")
    public void getUserByIdFail() throws Exception {
        when(mockUserRepository.findById(eq(1L))).thenReturn(Optional.empty());

        mockMvc.perform(get("/users/1")).andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(authorities = {"user"}, username = "user@mail.com", password = "123")
    @DisplayName("Should return forbidden if a non admin user tries to delete a user")
    public void deleteUserByUserIsForbidden() throws Exception {
        mockMvc.perform(delete("/users/1")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(authorities = {"ADMIN"}, username = "admin@mail.com", password = "123")
    @DisplayName("Should return deleted when a request to delete a user is made by and admin")
    public void deleteUserByAdminIsAllowed() throws Exception {
        UserEntity user = UserEntity.builder().id(1L).build();
        when(mockUserRepository.findById(eq(1L))).thenReturn(Optional.of(user));

        mockMvc.perform(delete("/users/1")).andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(authorities = {"ADMIN"}, username = "admin@mail.com", password = "123")
    @DisplayName("Should return not found when tring to delete a non existing user")
    public void deleteUserByAdminNotFound() throws Exception {
        when(mockUserRepository.findById(eq(1L))).thenReturn(Optional.empty());
        mockMvc.perform(delete("/users/1")).andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(authorities = {"USER"}, username = "user@mail.com", password = "123")
    @DisplayName("Should return a list of users")
    public void getListOfUsers() throws Exception {
        when(mockUserRepository.findAll()).thenReturn(List.of(
                UserEntity.builder().id(1L).email("userone@mail.com").role(RoleEntity.builder().id(1L).build()).build(),
                UserEntity.builder().id(2L).email("usertwo@mail.com").role(RoleEntity.builder().id(1L).build()).build()
        ));
        mockMvc.perform(get("/users")).andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)))
                .andExpect(jsonPath("$[0].email", Is.is("userone@mail.com")))
                .andExpect(jsonPath("$[0].id", Is.is(1)))
                .andExpect(jsonPath("$[1].email", Is.is("usertwo@mail.com")))
                .andExpect(jsonPath("$[1].id", Is.is(2)));
    }
}
