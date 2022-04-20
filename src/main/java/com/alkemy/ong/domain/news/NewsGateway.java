package com.alkemy.ong.domain.news;


public interface NewsGateway {

    News getDetails(Long id);
    News findById(Long id);
    News save(News news);
    void delete(Long id);

}
