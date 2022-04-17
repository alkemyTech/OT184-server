package com.alkemy.ong.web.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Builder
@Data
public class ActivityDto {
  @NotBlank(message = "Name is required")
  String name;

  @NotBlank(message = "Content is required")
  String content;

  @NotBlank(message = "Image is required")
  String image;
}
