package com.alkemy.ong.data.gateway;

import com.alkemy.ong.data.repository.NewsRepository;
import com.alkemy.ong.domain.gateway.NewsGateway;
import com.alkemy.ong.web.controller.dto.NewsDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class DefaultGateway implements NewsGateway {

    @Autowired
    NewsRepository newsRepository;
    @Override
    public NewsDTO save(NewsDTO newsDTO) {
        newsRepository.save(newsDTO);
        return new NewsDTO();
    }
}
