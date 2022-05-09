package com.alkemy.ong;


import com.alkemy.ong.data.entities.NewsEntity;
import com.alkemy.ong.data.repositories.NewsRepository;
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
public class News {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private NewsRepository newsRepo;
    private NewsEntity newsEntity = NewsEntity.builder()
                .id(1L)
                .name("news")
                .content("content")
                .image("/img.png")
                .categoryId(1L)
                .build();

    @Test
    @WithMockUser(authorities  = "ADMIN")
    @DisplayName("Save success case")
    public void saveSuccess() throws Exception{
        when(newsRepo.save(any(NewsEntity.class))).thenReturn(newsEntity);

        mockMvc.perform(post("/news")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Json.mapper().writeValueAsString(newsEntity)))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("news")))
                .andExpect(jsonPath("$.content", is("content")))
                .andExpect(jsonPath("$.image", is("/img.png")))
                .andExpect(jsonPath("$.categoryId", is(1)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(authorities  = "USER")
    @DisplayName("Save forbidden case by USER")
    public void saveByUserError() throws Exception{
        when(newsRepo.save(any(NewsEntity.class))).thenReturn(newsEntity);

        mockMvc.perform(post("/news")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Json.mapper().writeValueAsString(newsEntity)))
                .andExpect(status().isForbidden());
    }


}
