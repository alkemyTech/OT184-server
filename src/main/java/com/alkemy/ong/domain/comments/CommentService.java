package com.alkemy.ong.domain.comments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
  private final CommentGateway commentGateway;
  public Comment addComment(Long userId, String body, Long newsId) {
    return commentGateway.addComent(userId, body, newsId);
  }
}
