package com.alkemy.ong.data.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "comments")
@SQLDelete(sql = "UPDATE comment SET deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
@Builder
@Getter
@NoArgsConstructor
public class CommentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "user_id")
  private UserEntity user;

  @Column(name = "user_id")
  private Long userId;

  @Column(nullable = false)
  private String body;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "news_id")
  private NewsEntity news;

  @Column(name = "news_id")
  private Long newsId;

  @Column(name = "created_at", updatable = false, nullable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @Column(name = "is_deleted", nullable = false)
  private boolean isDeleted = Boolean.FALSE;
}
