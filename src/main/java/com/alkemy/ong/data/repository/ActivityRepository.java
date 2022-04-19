package com.alkemy.ong.data.repository;

import com.alkemy.ong.data.entities.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {
}