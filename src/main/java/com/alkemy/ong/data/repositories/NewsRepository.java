package com.alkemy.ong.data.repositories;

import com.alkemy.ong.data.entities.NewsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
    Page<NewsEntity> findAll(Pageable pageRequest);
}
