package com.alkemy.ong.domain.category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Category {
    private Long id;
    private String name;
    private String description;
    private String image;
}
