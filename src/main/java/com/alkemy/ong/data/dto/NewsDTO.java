package com.alkemy.ong.data.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
public class NewsDTO {
    private Long id;
    @NotEmpty (message = "The field Name must not be empty")
    private String name;
    @NotEmpty (message = "The field Content must not be empty")
    private String content;
    @NotEmpty(message = "The field Image must not be empty")
    private String image;
    private Long categoryId;
    private boolean isDeleted = Boolean.FALSE;
}
