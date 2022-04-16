package com.alkemy.ong.data.gateway;

import com.alkemy.ong.data.entity.NewsEntity;
import com.alkemy.ong.data.repository.NewsRepository;
import com.alkemy.ong.domain.gateway.NewsGateway;
import com.alkemy.ong.domain.model.News;
import com.alkemy.ong.web.controller.dto.NewsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultGateway implements NewsGateway {

    @Autowired
    NewsRepository newsRepository;
    @Override
    public News save(News news) {
        NewsEntity newsEntity = NewsEntity.builder().name(news.getName())
                .content(news.getContent())
                .image(news.getImage())
                .categoryId(news.getCategoryId())
                .type("news")
                .build();
        newsRepository.save(newsEntity);
        return news;
    }
}
