package com.alkemy.ong.data.repositories;

import com.alkemy.ong.data.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
