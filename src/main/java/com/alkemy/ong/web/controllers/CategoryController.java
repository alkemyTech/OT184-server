package com.alkemy.ong.web.controllers;

import com.alkemy.ong.domain.categories.Category;
import com.alkemy.ong.domain.categories.CategoryService;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.StringJoiner;

import static java.util.stream.Collectors.*;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController (CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    private ResponseEntity<List<CategoryBasicDTO>> getAllCategoryBasic(){

        List<Category> category = categoryService.findAll();
        List<CategoryBasicDTO> categoryBasicDTOS = category.stream().map(this::toDTOBasic).collect(toList());
        return ResponseEntity.ok().body(categoryBasicDTOS);
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
    void addLinkHeaderOnPagedResourceRetrieval(
            UriComponentsBuilder uriBuilder,
            HttpServletResponse response,
            Page p) {
        int page = p.getNumber();
        int totalPages = p.getTotalPages();
        String resourceName = p.getContent().get(0).getClass().getSimpleName().toLowerCase();
        uriBuilder.path( "" + resourceName );

        StringJoiner linkHeader = new StringJoiner(", ");

        if (hasNextPage(page, totalPages)){
            String uriForNextPage = constructNextPageUri(uriBuilder, page);
            linkHeader.add(createLinkHeader(uriForNextPage, "next"));
        }
        if (hasPreviousPage(page)) {
            String uriForPrevPage = constructPrevPageUri(uriBuilder, page);
            linkHeader.add(createLinkHeader(uriForPrevPage, "prev"));
        }

        response.addHeader("Link", linkHeader.toString());
    }

    String constructPrevPageUri(final UriComponentsBuilder uriBuilder, final int page) {
        return uriBuilder.replaceQueryParam("page", page - 1).build().encode().toUriString();
    }
    String constructNextPageUri(UriComponentsBuilder uriBuilder, int page) {
        return uriBuilder.replaceQueryParam("page", page + 1)
                .build()
                .encode()
                .toUriString();
    }
    boolean hasNextPage(final int page, final int totalPages) {
        return page < totalPages - 1;
    }

    boolean hasPreviousPage(final int page) {
        return page > 0;
    }

    public static String createLinkHeader(final String uri, final String rel) {
        return "<" + uri + ">; rel=\"" + rel + "\"";
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
