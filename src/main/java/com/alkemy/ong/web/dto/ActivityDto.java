package com.alkemy.ong.web.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Builder
@Data
public class ActivityDto {
  String name;

  String content;

  String image;
}
