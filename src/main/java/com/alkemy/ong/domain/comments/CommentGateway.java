package com.alkemy.ong.domain.comments;

public interface CommentGateway {
  Comment addComent(Long userId, String body, Long newsId);
}
