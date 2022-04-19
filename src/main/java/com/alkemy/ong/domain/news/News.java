package com.alkemy.ong.domain.news;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class News {
    private Long id;
    private String name;
    private String content;
    private String image;
    //private Category category;
    private Long categoryId;
    private String type;
}
