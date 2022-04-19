package com.alkemy.ong.domain.news;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService  {
    private final NewsGateway newsGateway;
    @Autowired
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
}
