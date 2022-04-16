package com.alkemy.ong.data.gateway;

import com.alkemy.ong.data.entity.CategoryEntity;
import com.alkemy.ong.data.repository.CategoryRepository;
import com.alkemy.ong.domain.category.Category;
import com.alkemy.ong.domain.category.CategoryGateway;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DefaultCategoryGateway implements CategoryGateway {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public Category findById(Long id) {
        return null;
    }

    @Override
    public Category save(Category category) {
        return null;
    }
}
