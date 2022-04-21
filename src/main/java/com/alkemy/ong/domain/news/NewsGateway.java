package com.alkemy.ong.domain.news;


import com.alkemy.ong.data.entities.NewsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NewsGateway {

    News getDetails(Long id);
    News findById(Long id);
    News save(News news);
    void delete(Long id);
    Page<NewsEntity> findAll(Pageable pageable);
}
