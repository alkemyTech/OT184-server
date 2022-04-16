package com.alkemy.ong.domain.model;

import lombok.Data;

@Data
public class News {
    private Long id;
    private String name;
    private String content;
    private String image;
    //private CategoryEntity category;
    private Long categoryId;
}
