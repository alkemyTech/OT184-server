package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.CategoryEntity;
import com.alkemy.ong.data.repositories.CategoryRepository;
import com.alkemy.ong.domain.categories.Category;
import com.alkemy.ong.domain.categories.CategoryGateway;
import com.alkemy.ong.domain.exceptions.ParamNotFound;
import org.springframework.stereotype.Component;

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

        return categoryRepository.findAll().stream().map(this::toModel).collect(toList());
    }

    @Override
    public Category findById(Long id) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(id);
        if (categoryEntity.isEmpty()){
            throw new ParamNotFound("ID");
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
