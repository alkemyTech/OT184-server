package com.alkemy.ong.web.controller;

import com.alkemy.ong.domain.category.Category;
import com.alkemy.ong.domain.category.CategoryService;
import com.alkemy.ong.web.dto.CategoryBasicDTO;
import com.alkemy.ong.web.dto.CategoryDTO;
import com.alkemy.ong.web.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<CategoryBasicDTO> getAllCategoryBasic(){

        CategoryBasicDTO categoryBasicDTOS = CategoryMapper.toDTOBasic(categoryService.findAll());
        return ResponseEntity.ok().body(categoryBasicDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryByID(@PathVariable Long id){
        Category category = categoryService.findById(id);
        CategoryDTO categoryDTO = CategoryMapper.toDTO(category);
        return ResponseEntity.ok(categoryDTO);
    }

    @PostMapping
    public  ResponseEntity<CategoryDTO> save(@RequestBody CategoryDTO categoryDTO){
        Category category = CategoryMapper.toModel(categoryDTO);
        Category categorySaved = categoryService.save(category);
        CategoryDTO result = CategoryMapper.toDTO(categorySaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
