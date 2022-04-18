package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.CategoryEntity;
import com.alkemy.ong.data.repositories.CategoryRepository;
import com.alkemy.ong.domain.categories.Category;
import com.alkemy.ong.domain.categories.CategoryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.*;

@Component
public class DefaultCategoryGateway implements CategoryGateway {

    private final CategoryRepository categoryRepository;
    
    public DefaultCategoryGateway(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        List<CategoryEntity> entities = categoryRepository.findAll();
        List<Category> categories = entities.stream().map(this::toModel).collect(toList());
        return categories;
    }

    @Override
    public Category findById(Long id) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(id);
        if (categoryEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Category category = toModel(categoryEntity.get());
        return category;
    }

    @Override
    public Category save(Category category) {
        CategoryEntity categoryEntity = toEntity(category);
        CategoryEntity categorySaved = categoryRepository.save(categoryEntity);
        Category result = toModel(categorySaved);
        return result;
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
