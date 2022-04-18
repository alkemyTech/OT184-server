package com.alkemy.ong.domain.gateways;

import com.alkemy.ong.domain.models.Category;

import java.util.List;

public interface CategoryGateway {

    List<Category> findAll();

    Category findById(Long id);

    Category save(Category category);
}
