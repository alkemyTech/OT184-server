package com.alkemy.ong.web.controllers;

import com.alkemy.ong.domain.categories.Category;
import com.alkemy.ong.domain.categories.CategoryService;
import com.alkemy.ong.domain.utils.PageModel;
import com.alkemy.ong.web.controllers.utils.PageResponse;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

import static java.util.stream.Collectors.toList;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController (CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    private ResponseEntity<PageResponse<CategoryBasicDTO>> getAllCategoryBasicByPage(@RequestParam("page") int pageNumber){

        PageModel<Category> page = categoryService.findByPage(pageNumber);
        String path = "/categories";
        PageResponse response = PageResponse.builder()
                .content(page.getContent()
                        .stream()
                        .map(this::toDTOBasic)
                        .collect(toList()))
                .build();
        response.setResponse(path,pageNumber,page.getTotalPages(),page.isFirst(),page.isLast());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    private ResponseEntity<CategoryDTO> getCategoryByID(@PathVariable Long id){
        Category category = categoryService.findById(id);
        CategoryDTO categoryDTO = toDTO(category);
        return ResponseEntity.ok().body(categoryDTO);
    }

    @PostMapping
    private  ResponseEntity<CategoryDTO> save(@Valid @RequestBody CategoryDTO categoryDTO){
        Category category = toModel(categoryDTO);
        Category categorySaved = categoryService.save(category);
        CategoryDTO result = toDTO(categorySaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();}

    @PutMapping("/{id}")
    private ResponseEntity<CategoryDTO> update(@PathVariable Long id, @Valid @RequestBody CategoryDTO categoryDTO){
        Category categoryUpdated = categoryService.update(id, toModel(categoryDTO));
        return  ResponseEntity.ok().body(toDTO(categoryUpdated));

    }

    @Data
    @Builder
    private static class CategoryDTO {
        private Long id;
        @NotEmpty(message = "The field must be a name")
        @Pattern(regexp = "[A-Za-z]*$")
        private String name;
        private String description;
        private String image;
    }

    @Data
    @Builder
    private static class CategoryBasicDTO {
        private Long id;
        private String name;
    }

    private CategoryBasicDTO toDTOBasic(Category category) {
        return CategoryBasicDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    private CategoryDTO toDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
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
