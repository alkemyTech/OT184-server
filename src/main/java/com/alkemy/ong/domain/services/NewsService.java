package com.alkemy.ong.domain.services;
import com.alkemy.ong.domain.models.News;
import org.springframework.stereotype.Service;

@Service
public interface NewsService {

    News getDetails(Long id);
}
