package com.alkemy.ong.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Activity {
  String name;
  String content;
  String image;
}
