package com.alkemy.ong.domain.services;
import com.alkemy.ong.domain.gateways.NewsGateway;
import com.alkemy.ong.domain.models.News;
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
