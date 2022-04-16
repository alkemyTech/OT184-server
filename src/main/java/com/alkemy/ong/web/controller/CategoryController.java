package com.alkemy.ong.web.controller;

import com.alkemy.ong.web.dto.CategoryBasicDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @GetMapping
    public ResponseEntity<List<CategoryBasicDTO>> getAllCategoryBasic(){

        List<CategoryBasicDTO> categoryBasicDTO = new ArrayList<>();
        return ResponseEntity.ok().body(categoryBasicDTO);
    }
}
