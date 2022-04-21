package com.alkemy.ong.domain.news;

import com.alkemy.ong.data.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService  {
    private final NewsGateway newsGateway;
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


    @Autowired
    NewsRepository newsRepository;
    public List<News> getAllPageable(int pageNumber) {
        final int SIZE = 10;
        return newsGateway.getListByPage(pageNumber,SIZE);
    }
}
