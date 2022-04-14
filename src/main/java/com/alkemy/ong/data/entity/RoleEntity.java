package com.alkemy.ong.data.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "roles")
@SQLDelete(sql = "UPDATE roles SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class RoleEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  private String description;

  @Column(name = "created_at")
  @CreationTimestamp
  private LocalDateTime createdAt = LocalDateTime.now();

  @Column(name = "updated_at")
  @UpdateTimestamp
  private LocalDateTime updatedAt = LocalDateTime.now();

  @Column(name = "is_deleted")
  private boolean isDeleted = Boolean.FALSE;
}
