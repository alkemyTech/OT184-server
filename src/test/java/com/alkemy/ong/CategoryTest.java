package com.alkemy.ong;

import com.alkemy.ong.data.entities.CategoryEntity;
import com.alkemy.ong.data.repositories.CategoryRepository;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class CategoryTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllCategoryBasicByPageTest() throws Exception{
        var categoryEntity1 = createCategory(1L,"Health", "Health is very important for the world", "health.jpg");
        var categoryEntity2 = createCategory(2L,"Greenpeace", "Nature protection", "greenpeace.jpg");
        var categoryEntity3 = createCategory(2L,"UNICEF", "Best solidarity", "unicef.jpg");

        List<CategoryEntity> categoryEntities = new ArrayList<>();
        categoryEntities.add(categoryEntity1);
        categoryEntities.add(categoryEntity2);
        categoryEntities.add(categoryEntity3);

        when(categoryRepository.findAll()).thenReturn(categoryEntities);

        mockMvc.perform(MockMvcRequestBuilders.get("/categories").param("Page","0").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryEntity1)))
                .andExpect((ResultMatcher) jsonPath("$[0].id", is(1)))
                .andExpect((ResultMatcher) jsonPath("$[1].id", is(2)))
                .andExpect((ResultMatcher) jsonPath("$[0].name", is("Health")))
                .andExpect(status().isOk());

    }

    @Test
    public void getCategoryByIDTest() throws Exception{
        var categoryEntity = createCategory(1L,"Health", "Health is very important for the world", "health.jpg");

        when(categoryRepository.findById(categoryEntity.getId())).thenReturn(Optional.of(categoryEntity));

        mockMvc.perform(MockMvcRequestBuilders.get("/categories/1").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryEntity)))
                .andExpect((ResultMatcher) jsonPath("$.id", is(1)))
                .andExpect((ResultMatcher) jsonPath("$.name", is("Health")))
                .andExpect((ResultMatcher) jsonPath("$.description", is("Health is very important for the world")))
                .andExpect((ResultMatcher) jsonPath("$.image", is("health.jpg")))
                .andExpect(status().isOk());
    }

    @Test
    public void getCategoryByIDTestError() throws Exception{

        when(categoryRepository.findById(1234L)).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform((MockMvcRequestBuilders.get("/categories/1234")).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    private CategoryEntity createCategory(Long id, String name,String description, String image ){
        CategoryEntity category = new CategoryEntity();
        category.setId(id);
        category.setName(name);
        category.setDescription(description);
        category.setImage(image);
        category.setIsDeleted(false);
        return category;
    }
}
