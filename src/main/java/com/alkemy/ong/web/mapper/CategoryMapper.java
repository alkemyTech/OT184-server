package com.alkemy.ong.web.mapper;

import com.alkemy.ong.domain.category.Category;
import com.alkemy.ong.web.dto.CategoryBasicDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public static CategoryBasicDTO toDTOBasic(Category category) {
        return CategoryBasicDTO.builder()
                .name(category.getName())
                .build();
    }
}
