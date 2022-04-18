package com.alkemy.ong.web.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryDTO {

    private String name;

    private String description;

    private String image;
}
