package com.alkemy.ong.data.repositories;


import com.alkemy.ong.data.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<UserEntity, Long> {
}
