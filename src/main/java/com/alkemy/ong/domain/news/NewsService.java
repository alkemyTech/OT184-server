package com.alkemy.ong.domain.news;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService  {
    private final NewsGateway newsGateway;
    @Value("${spring.application.pagingSize}")
    int pageSize;
    public NewsService(NewsGateway newsGateway){
        this.newsGateway = newsGateway;
    }
    public News getDetails(Long id) {
        return newsGateway.getDetails(id);
    }
    public News save(News news) {
        news.setType("news");
        return newsGateway.save(news);
    }
    public void delete(Long id) {
        newsGateway.delete(id);
    }
    public List<News> findByPage(int pageNumber) {
        return newsGateway.findByPage(pageNumber, pageSize);
    }
}
