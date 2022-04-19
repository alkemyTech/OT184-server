package com.alkemy.ong.domain.models;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class News {
    private Long id;
    private String name;
    private String content;
    private String image;
    //private CategoryEntity category;
    private Long categoryId;
    private String type;
}
