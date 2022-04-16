package com.alkemy.ong.domain.category;

public interface CategoryGateway {

    Category findAll();

    Category findById(Long id);
}
