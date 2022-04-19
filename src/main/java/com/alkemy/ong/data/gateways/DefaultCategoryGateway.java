package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.CategoryEntity;
import com.alkemy.ong.data.repositories.CategoryRepository;
import com.alkemy.ong.domain.category.Category;
import com.alkemy.ong.domain.category.CategoryGateway;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.*;

@Component
public class DefaultCategoryGateway implements CategoryGateway {

    private final CategoryRepository categoryRepository;
    
    public DefaultCategoryGateway(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll().stream().map(this::toModel).collect(toList());
    }

    @Override
    public Category findById(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID"));
        return toModel(categoryEntity);
    }

    @Override
    public Category save(Category category) {
        CategoryEntity categoryEntity = toEntity(category);
        return toModel(categoryRepository.save(categoryEntity));
    }

    private Category toModel(CategoryEntity categoryEntity) {
        return Category.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .description(categoryEntity.getDescription())
                .image(categoryEntity.getImage())
                .build();

    }

    private CategoryEntity toEntity(Category category) {
        return CategoryEntity.builder()
                .name(category.getName())
                .description(category.getDescription())
                .image(category.getImage())
                .isDeleted(false)
                .build();
    }


}
