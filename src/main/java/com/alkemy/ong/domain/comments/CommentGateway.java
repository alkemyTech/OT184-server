package com.alkemy.ong.domain.comments;

public interface CommentGateway {

    Comment create(Comment comment);

    void delete(Long id);
}
