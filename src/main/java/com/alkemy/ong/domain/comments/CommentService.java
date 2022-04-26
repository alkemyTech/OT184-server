package com.alkemy.ong.domain.comments;

import org.springframework.stereotype.Service;

@Service
public class CommentService {

  private final CommentGateway commentGateway;

  public CommentService(CommentGateway commentGateway) {
    this.commentGateway = commentGateway;
  }

  public Comment create(Comment comment) {
    return commentGateway.create(comment);
  }

  public void delete(Long id){
    commentGateway.delete(id);
  }
}
