package com.alkemy.ong.domain.news;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService  {

    @Autowired
    NewsGateway newsGateway;

    public News getDetails(Long id) {
        return newsGateway.getDetails(id);
    }
}
