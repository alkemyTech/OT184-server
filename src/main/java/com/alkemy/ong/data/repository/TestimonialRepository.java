package com.alkemy.ong.data.repository;

import com.alkemy.ong.data.entity.TestimonialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestimonialRepository extends JpaRepository<TestimonialEntity, Long> {
}
