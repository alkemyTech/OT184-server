package com.alkemy.ong.domain.services.impl;
import com.alkemy.ong.domain.gateways.NewsGateway;
import com.alkemy.ong.domain.models.News;
import com.alkemy.ong.domain.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsGateway newsGateway;

    @Override
    public News getDetails(Long id) {
        return newsGateway.getDetails(id);
    }
}
