package com.alkemy.ong.data.gateway;

import com.alkemy.ong.data.entity.NewsEntity;
import com.alkemy.ong.data.mapper.NewsModelMapper;
import com.alkemy.ong.data.repository.NewsRepository;
import com.alkemy.ong.domain.gateway.NewsGateway;
import com.alkemy.ong.domain.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultGateway implements NewsGateway {

    @Autowired
    NewsRepository newsRepository;
    @Autowired
    NewsModelMapper newsModelMapper;

    /**
     * Recibe un objeto de tipo model, lo guarda en la db y devuelve el mismo objeto con la id generada.
     * @param news
     * @return news
     */
    @Override
    public News save(News news) {
        NewsEntity newsEntity = newsModelMapper.modelToEntity(news);
        News resultNews = newsModelMapper.entityToModel(newsRepository.save(newsEntity));
        return resultNews;
    }
}
