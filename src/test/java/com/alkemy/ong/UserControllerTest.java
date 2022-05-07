package com.alkemy.ong;

import com.alkemy.ong.data.entities.RoleEntity;
import com.alkemy.ong.data.entities.UserEntity;
import com.alkemy.ong.data.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
                .firstName("Mock")
                .lastName("User")
                .role(RoleEntity.builder().id(1L).name("user").description("user level access").build())
                .photo("https://www.s3.bucket/mock_user.jpg")
                .build();

        when(mockUserRepository.findById(eq(1L))).thenReturn(Optional.of(userEntity));
        when(mockUserRepository.findByEmail(eq("user"))).thenReturn(userEntity);

        mockMvc.perform(get("/users/1")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = {"user", "2"}, username = "user@mail.com", password = "123")
    @DisplayName("Should return the authenticated user if requested by a non admin user")
    public void getUserByIdSuccessUser() throws Exception {
        UserEntity userEntity = UserEntity.builder()
                .id(2L)
                .email("user@mail.com")
                .firstName("Mock")
                .lastName("User")
                .role(RoleEntity.builder().id(1L).name("USER").description("User level access").build())
                .build();

        when(mockUserRepository.findByEmail(eq("user@mail.com"))).thenReturn(userEntity);

        mockMvc.perform(get("/users/1")).andExpect(status().isOk());
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
}
