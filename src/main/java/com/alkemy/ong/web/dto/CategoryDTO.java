package com.alkemy.ong.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
public class CategoryDTO {

    @NotEmpty
    @Pattern(regexp = "[A-Za-z]*$")
    private String name;

    private String description;

    private String image;
}
