package com.alkemy.ong.domain.gateway;

import com.alkemy.ong.domain.model.News;
import com.alkemy.ong.web.controller.dto.NewsDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public interface NewsGateway {
    News save(News news);
}
