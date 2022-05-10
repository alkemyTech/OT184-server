package com.alkemy.ong;

import com.alkemy.ong.data.entities.ActivityEntity;
import com.alkemy.ong.data.repositories.ActivityRepository;
import com.alkemy.ong.web.controllers.ActivityController;
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

import java.util.Optional;

import static com.alkemy.ong.web.controllers.ActivityController.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
    @DisplayName("admins should be able to create activities")
    public void createActivitySuccess() throws Exception {
        String content = "Activity Content";
        String name = "Activity Name";
        String image = "https://bucket.com/image.jpg";

        ActivityDto activityDto = getActivity(content, name, image);

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
    @DisplayName("non admin users shouldn't be able to create activities")
    public void createActivityFailure() throws Exception {

        ActivityDto activityDto = getActivity("Activity Content", "Activity Name", "https://bucket.com/image.jpg");

        mockMvc.perform(post("/activities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Json.mapper().writeValueAsString(activityDto))
                )
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(authorities = {"ADMIN", "2"}, username = "admin@mail.com", password = "123")
    @DisplayName("trying to create a invalid activity should return error")
    public void createInvalidActivityByAdmin() throws Exception {

        ActivityDto activityDto = ActivityDto.builder()
                .content("Activity Content")
                .image("https://bucket.com/image.jpg")
                .build();

        mockMvc.perform(post("/activities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Json.mapper().writeValueAsString(activityDto))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities = {"ADMIN", "2"}, username = "admin@mail.com", password = "123")
    @DisplayName("admins should be able to update activities")
    public void updateActivitySuccess() throws Exception {

        String contentAfter = "After activity content";
        String imageAfter = "https://bucket.com/after.jpg";
        String nameAfter = "After activity name";

        ActivityDto activityDto = ActivityDto.builder()
                .id(1L)
                .content(contentAfter)
                .image(imageAfter)
                .name(nameAfter)
                .build();

        when(mockActivityRepository.findById(eq(1L))).thenReturn(Optional.of(ActivityEntity.builder()
                .id(activityDto.getId())
                .name("Before activity content")
                .image("https://bucket.com/before.jpg")
                .content("Before activity name")
                .build()));

        when(mockActivityRepository.save(any(ActivityEntity.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);

        mockMvc.perform(put("/activities/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Json.mapper().writeValueAsString(activityDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Is.is(1)))
                .andExpect(jsonPath("$.name", Is.is(nameAfter)))
                .andExpect(jsonPath("$.content", Is.is(contentAfter)))
                .andExpect(jsonPath("$.image", Is.is(imageAfter)));
    }

    @Test
    @WithMockUser(authorities = {"USER", "2"}, username = "user@mail.com", password = "123")
    @DisplayName("non admin users shouldn't be able to update activities")
    public void updateActivityNonAdminFail() throws Exception {
        mockMvc.perform(put("/activities/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Json.mapper().writeValueAsString(ActivityDto.builder()
                                .id(1L)
                                .name("name")
                                .content("content")
                                .image("image")
                        )))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(authorities = {"ADMIN", "2"}, username = "user@mail.com", password = "123")
    @DisplayName("should return bad request when trying to update an activity with missing fields in the request")
    public void nose() throws Exception {
        mockMvc.perform(put("/activities/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Json.mapper().writeValueAsString(ActivityDto.builder()
                                .id(1L)
                                .name("name")
                                .image("image")
                        )))
                .andExpect(status().isBadRequest());
    }

    private ActivityDto getActivity(String content, String name, String image) {
        return ActivityDto.builder()
                .content(content)
                .name(name)
                .image(image)
                .build();
    }
}
