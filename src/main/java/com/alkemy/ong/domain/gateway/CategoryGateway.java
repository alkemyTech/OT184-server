package com.alkemy.ong.domain.gateway;

import com.alkemy.ong.domain.model.Category;

import java.util.List;

public interface CategoryGateway {

    List<Category> findAll();

    Category findById(Long id);

    Category save(Category category);
}
