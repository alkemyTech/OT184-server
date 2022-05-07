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
    @WithMockUser(authorities = "ADMIN", username = "user", password = "123")
    @DisplayName("Should return the requested user if the request has the ADMIN authority")
    public void getUserByIdSuccess() throws Exception {
        String email = "user@mail.com";
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .email(email)
                .firstName("Mock")
                .lastName("User")
                .role(RoleEntity.builder().id(1L).name("ADMIN").description("Admin level access").build())
                .photo("https://www.s3.bucket/mock_user.jpg")
                .build();

        when(mockUserRepository.findById(eq(1L))).thenReturn(Optional.of(userEntity));
        when(mockUserRepository.findByEmail(eq("user"))).thenReturn(userEntity);

        mockMvc.perform(get("/users/1")).andExpect(status().isOk());
    }

}
