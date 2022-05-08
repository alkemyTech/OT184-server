package com.alkemy.ong;

import com.alkemy.ong.data.entities.ActivityEntity;
import com.alkemy.ong.data.repositories.ActivityRepository;
import io.swagger.v3.core.util.Json;
import org.hamcrest.core.Is;
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

import static com.alkemy.ong.web.controllers.ActivityController.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@DisplayName("Given a user controller")
public class ActivityControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ActivityRepository mockActivityRepository;

    @Test
    @WithMockUser(authorities = {"ADMIN", "2"}, username = "admin@mail.com", password = "123")
    @DisplayName("an admin should be able to create an activity")
    public void createUserSuccess() throws Exception {
        String content = "Activity Content";
        String name = "Activity Name";
        String image = "https://bucket.com/image.jpg";

        ActivityDto activityDto = ActivityDto.builder()
                .content(content)
                .name(name)
                .image(image)
                .build();

        when(mockActivityRepository
                .save(any(ActivityEntity.class)))
                .thenReturn(ActivityEntity.builder()
                        .id(1L)
                        .content(content)
                        .name(name)
                        .image(image)
                        .build());

        mockMvc.perform(post("/activities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Json.mapper().writeValueAsString(activityDto))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Is.is(1)))
                .andExpect(jsonPath("$.content", Is.is(content)))
                .andExpect(jsonPath("$.name", Is.is(name)))
                .andExpect(jsonPath("$.image", Is.is(image)));
    }

    @Test
    @WithMockUser(authorities = {"USER", "2"}, username = "user@mail.com", password = "123")
    @DisplayName("a user should not be able to create an activity")
    public void createActivityFailure() throws Exception {

        ActivityDto activityDto = ActivityDto.builder()
                .content("Activity Content")
                .name("Activity Name")
                .image("https://bucket.com/image.jpg")
                .build();

        mockMvc.perform(post("/activities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Json.mapper().writeValueAsString(activityDto))
                )
                .andExpect(status().isForbidden());
    }
}
