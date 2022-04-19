package com.alkemy.ong.domain.news;

import org.springframework.stereotype.Service;

@Service
public interface NewsGateway {

    News getDetails(Long id);
    News findById(Long id);

    News save(News news);
}
