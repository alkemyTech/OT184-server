package com.alkemy.ong.domain.news;

import com.alkemy.ong.data.entities.NewsEntity;
import com.alkemy.ong.data.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Page<NewsEntity> getAllPageable(int pageNumber) {
        final int SIZE = 10;
        Pageable pageable = PageRequest.of(pageNumber, SIZE);
        Page<NewsEntity> returnPage = newsGateway.findAll(pageable);


        return returnPage;
    }
}
