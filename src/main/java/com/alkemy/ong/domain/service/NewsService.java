package com.alkemy.ong.domain.service;

import com.alkemy.ong.web.controller.dto.NewsDTO;
import org.springframework.stereotype.Service;

@Service
public interface NewsService {
    NewsDTO save(NewsDTO newsDTO);
}
