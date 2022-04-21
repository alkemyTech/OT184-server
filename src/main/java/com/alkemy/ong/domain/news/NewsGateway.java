package com.alkemy.ong.domain.news;


import java.util.List;

public interface NewsGateway {

    News getDetails(Long id);
    News findById(Long id);
    News save(News news);
    void delete(Long id);
    List<News> findByPage(int pageNumber, int size);
}
