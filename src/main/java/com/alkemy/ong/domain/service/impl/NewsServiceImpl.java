package com.alkemy.ong.domain.service.impl;

import com.alkemy.ong.domain.gateway.NewsGateway;
import com.alkemy.ong.domain.service.NewsService;
import com.alkemy.ong.web.controller.dto.NewsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsGateway newsGateway;
    public NewsDTO save(NewsDTO newsDTO) {
        newsGateway.save(newsDTO);

        NewsDTO returnDTO = null;
        return returnDTO;
    }
}
