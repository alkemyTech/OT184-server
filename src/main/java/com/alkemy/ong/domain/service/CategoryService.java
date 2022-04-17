package com.alkemy.ong.domain.service;

import com.alkemy.ong.domain.gateway.CategoryGateway;
import com.alkemy.ong.domain.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryGateway categoryGateway;

    public List<Category> findAll(){
        return categoryGateway.findAll();
    }

    public Category findById(Long id){return categoryGateway.findById(id);}

    public Category save(Category category){return categoryGateway.save(category);}
}
