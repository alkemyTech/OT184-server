package com.alkemy.ong;

import com.alkemy.ong.domain.activity.ActivityService;
import com.alkemy.ong.web.controller.ActivityController;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Given an activity controller")
public class ActivityControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ActivityService activityService;

  @Transactional
  @Test
  @DisplayName("should return 201 when creating valid activity")
  public void createValidActivity() throws Exception {
    ActivityController.ActivityDto activityDto = ActivityController.ActivityDto.builder()
        .name("Activity").content("Content").image("https:www.bucket.com/image.jpg")
        .build();

    mockMvc.perform(
            post("/activities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString(activityDto))
        )
        .andExpect(status().isCreated());
  }

  @Transactional
  @Test
  @DisplayName("should return 400 when creating an invalid activity")
  public void createInvalidActivity() throws Exception {
    ActivityController.ActivityDto activityDto = ActivityController.ActivityDto.builder()
        .name("Activity").content("").image("https://www.bucket.com/image.jpg")
        .build();

    mockMvc.perform(
            post("/activities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getMapper().writeValueAsString(activityDto))
        )
        .andExpect(status().isBadRequest());
  }

  public static ObjectMapper getMapper() {
    var mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    mapper.registerModule(new JavaTimeModule());
    return mapper;
  }
}
