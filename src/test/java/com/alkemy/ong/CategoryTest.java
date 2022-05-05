package com.alkemy.ong;

import com.alkemy.ong.data.entities.CategoryEntity;
import com.alkemy.ong.data.repositories.CategoryRepository;
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
