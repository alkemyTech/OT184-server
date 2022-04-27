package com.alkemy.ong.domain.comments;

import java.util.List;

public interface CommentGateway {

    List<Comment> findAll();

    Comment create(Comment comment);

    void delete(Long id);

    List<Comment> findAllByNewsId(Long id);
}
