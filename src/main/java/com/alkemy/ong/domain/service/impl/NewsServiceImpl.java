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


    @Override
    public void delete(Long id) {
        
    }

    @Override
    public News findById(Long id) {
        return null;
    }
}
