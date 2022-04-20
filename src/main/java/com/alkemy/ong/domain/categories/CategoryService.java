package com.alkemy.ong.domain.categories;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryGateway categoryGateway;

    public CategoryService(CategoryGateway categoryGateway){
        this.categoryGateway = categoryGateway;
    }

    public List<Category> findAll(){
        return categoryGateway.findAll();
    }

    public Category findById(Long id){return categoryGateway.findById(id);}

    public Category save(Category category){return categoryGateway.save(category);}

    public void delete (Long id){categoryGateway.delete(id);}
}
