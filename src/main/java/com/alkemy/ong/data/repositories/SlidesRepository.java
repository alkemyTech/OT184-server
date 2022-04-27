package com.alkemy.ong.data.repositories;


import com.alkemy.ong.data.entities.SlidesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlidesRepository extends JpaRepository<SlidesEntity,Long> {

    @Query(value = "SELECT * FROM slides s WHERE s.organization_id = :id ORDER BY s.slide_order",
            nativeQuery = true)
    List<SlidesEntity> orderBySlide(Long id);
}
