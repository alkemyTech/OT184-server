package com.alkemy.ong.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Category {

    private String name;

    private String description;

    private String image;
}