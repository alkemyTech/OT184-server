package com.alkemy.ong.domain.comments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentGateway commentGateway;

  public Comment save(Comment comment) {
    return commentGateway.save(comment);
  }

  public void delete(Long id){
    commentGateway.delete(id);
  }
}
