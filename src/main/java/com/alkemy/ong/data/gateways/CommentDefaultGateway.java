package com.alkemy.ong.data.gateways;

import com.alkemy.ong.domain.comments.Comment;
import com.alkemy.ong.domain.comments.CommentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentDefaultGateway implements CommentGateway {
  private final CommentRepository commentRepository;

  @Override
  public Comment addComent(Long userId, String body, Long newsId) {
    return commentRepository.save();
  }
}
