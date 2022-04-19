package com.alkemy.ong.domain.service;
import com.alkemy.ong.domain.model.News;
import org.springframework.stereotype.Service;

@Service
public interface NewsService {
    News save(News news);
    void delete(Long id);
    News findById(Long id);
}
