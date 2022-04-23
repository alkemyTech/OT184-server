package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.CommentEntity;
import com.alkemy.ong.data.entities.NewsEntity;
import com.alkemy.ong.data.entities.UserEntity;
import com.alkemy.ong.data.repositories.CommentRepository;
import com.alkemy.ong.data.repositories.NewsRepository;
import com.alkemy.ong.data.repositories.UserRepository;
import com.alkemy.ong.domain.comments.Comment;
import com.alkemy.ong.domain.comments.CommentGateway;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentDefaultGateway implements CommentGateway {
  private final CommentRepository commentRepository;

  public Comment save(Comment comment) {
    return toModel(commentRepository.save(toEntity(comment)));
  }

  private CommentEntity toEntity(Comment comment) {
    return CommentEntity
        .builder()
        .id(comment.getId())
        .userId(comment.getUserId())
        .newsId(comment.getNewsId())
        .body(comment.getBody())
        .build();
  }

  private Comment toModel(CommentEntity commentEntity) {
    return Comment
        .builder()
        .id(commentEntity.getId())
        .userId(commentEntity.getUserId())
        .newsId(commentEntity.getNewsId())
        .body(commentEntity.getBody())
        .build();
  }
}
