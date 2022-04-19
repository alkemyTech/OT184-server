package com.alkemy.ong.data.repository;

import com.alkemy.ong.data.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<UserEntity, Long> {
}
