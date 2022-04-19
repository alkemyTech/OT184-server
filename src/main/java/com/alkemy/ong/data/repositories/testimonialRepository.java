package com.alkemy.ong.data.repositories;

import com.alkemy.ong.data.entities.testimonialEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface testimonialRepository extends JpaRepository<testimonialEntity, Long> {
}
