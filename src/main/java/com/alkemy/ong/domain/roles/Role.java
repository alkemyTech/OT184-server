package com.alkemy.ong.domain.roles;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class Role {
  private Long id;

  private String name;

  private String description;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  private Boolean isDeleted;
}
