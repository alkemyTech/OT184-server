package com.alkemy.ong.web.controller;

import com.alkemy.ong.domain.category.Category;
import com.alkemy.ong.domain.category.CategoryService;
import com.alkemy.ong.web.dto.CategoryBasicDTO;
import com.alkemy.ong.web.dto.CategoryDTO;
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

        CategoryBasicDTO categoryBasicDTOS = toDTOBasic(categoryService.findAll());
        return ResponseEntity.ok().body(categoryBasicDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryByID(@PathVariable Long id){
        Category category = categoryService.findById(id);
        CategoryDTO categoryDTO = toDTO(category);
        return ResponseEntity.ok(categoryDTO);
    }

    @PostMapping
    public  ResponseEntity<CategoryDTO> save(@RequestBody CategoryDTO categoryDTO){
        Category category = toModel(categoryDTO);
        Category categorySaved = categoryService.save(category);
        CategoryDTO result = toDTO(categorySaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    public static CategoryBasicDTO toDTOBasic(Category category) {
        return CategoryBasicDTO.builder()
                .name(category.getName())
                .build();
    }

    public static CategoryDTO toDTO(Category category) {
        return CategoryDTO.builder()
                .name(category.getName())
                .description(category.getDescription())
                .image(category.getImage())
                .build();
    }

    public static Category toModel(CategoryDTO categoryDTO){
        return Category.builder()
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .image(categoryDTO.getImage())
                .build();
    }
}
