package com.alkemy.ong.web.mapper;

import com.alkemy.ong.domain.category.Category;
import com.alkemy.ong.web.dto.CategoryBasicDTO;
import com.alkemy.ong.web.dto.CategoryDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public static CategoryBasicDTO toDTOBasic(Category category) {
        return CategoryBasicDTO.builder()
                .name(category.getName())
                .build();
    }

    public static CategoryDTO toDTO(Category category) {
        return CategoryDTO.builder()
                .name(category.getName())
                .description(category.getDescription())
                .image(category.getImage())
                .build();
    }
}
