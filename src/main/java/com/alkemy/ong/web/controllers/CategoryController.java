package com.alkemy.ong.web.controllers;

import com.alkemy.ong.domain.models.Category;
import com.alkemy.ong.domain.services.CategoryService;
import com.alkemy.ong.web.dtos.CategoryBasicDTO;
import com.alkemy.ong.web.dtos.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    private CategoryController (CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    private ResponseEntity<List<CategoryBasicDTO>> getAllCategoryBasic(){

        List<Category> category = categoryService.findAll();
        List<CategoryBasicDTO> categoryBasicDTOS = category.stream().map(this::toDTOBasic).collect(Collectors.toList());
        return ResponseEntity.ok().body(categoryBasicDTOS);
    }

    @GetMapping("/{id}")
    private ResponseEntity<CategoryDTO> getCategoryByID(@PathVariable Long id){
        Category category = categoryService.findById(id);
        CategoryDTO categoryDTO = toDTO(category);
        return ResponseEntity.ok().body(categoryDTO);
    }

    @PostMapping
    private  ResponseEntity<CategoryDTO> save(@RequestBody CategoryDTO categoryDTO){
        Category category = toModel(categoryDTO);
        Category categorySaved = categoryService.save(category);
        CategoryDTO result = toDTO(categorySaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    private CategoryBasicDTO toDTOBasic(Category category) {
        return CategoryBasicDTO.builder()
                .name(category.getName())
                .build();
    }

    private CategoryDTO toDTO(Category category) {
        return CategoryDTO.builder()
                .name(category.getName())
                .description(category.getDescription())
                .image(category.getImage())
                .build();
    }

    private Category toModel(CategoryDTO categoryDTO){
        return Category.builder()
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .image(categoryDTO.getImage())
                .build();
    }
}
