package com.alkemy.ong.domain.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryGateway categoryGateway;

    public Category findAll(){
        return categoryGateway.findAll();
    }
}
