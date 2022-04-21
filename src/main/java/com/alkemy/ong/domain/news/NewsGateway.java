package com.alkemy.ong.domain.news;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NewsGateway {

    News getDetails(Long id);
    News findById(Long id);
    News save(News news);
    void delete(Long id);
    Page<News> findAll(Pageable pageable);
}
