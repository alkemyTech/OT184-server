package com.alkemy.ong.domain.roles;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
  private Long id;

  private String name;

  private String description;

}
