package com.alkemy.ong.domain.service.impl;
import com.alkemy.ong.domain.gateway.NewsGateway;
import com.alkemy.ong.domain.model.News;
import com.alkemy.ong.domain.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsGateway newsGateway;

    public News save(News news) {
        news.setType("news");
        return newsGateway.save(news);
    }
}
