package com.alkemy.ong.data.gateway;

import com.alkemy.ong.data.entity.CategoryEntity;
import com.alkemy.ong.data.repository.CategoryRepository;
import com.alkemy.ong.domain.category.Category;
import com.alkemy.ong.domain.category.CategoryGateway;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultCategoryGateway implements CategoryGateway {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        List<CategoryEntity> entities = categoryRepository.findAll();
        List<Category> categories = entities.stream().map(this::toModel).collect(Collectors.toList());
        return categories;
    }

    @Override
    public Category findById(Long id) {
        CategoryEntity categoryEntity = categoryRepository.getById(id);
        Category category = toModel(categoryEntity);
        return category;
    }

    @Override
    public Category save(Category category) {
        return null;
    }

    private Category toModel(CategoryEntity categoryEntity) {
        return Category.builder()
                .name(categoryEntity.getName())
                .description(categoryEntity.getDescription())
                .image(categoryEntity.getImage())
                .build();

    }
}
