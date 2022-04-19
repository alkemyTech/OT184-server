package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.NewsEntity;
import com.alkemy.ong.data.exceptions.ParamNotFoundException;
import com.alkemy.ong.data.repositories.NewsRepository;
import com.alkemy.ong.domain.news.NewsGateway;
import com.alkemy.ong.domain.news.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DefaultNewsGateway implements NewsGateway {

    private final NewsRepository newsRepository;

    @Autowired
    public DefaultNewsGateway(NewsRepository newsRepository){
        this.newsRepository = newsRepository;
    }

    public News findById(Long id) {
        Optional<NewsEntity> optional = newsRepository.findById(id);
        if(!optional.isPresent()){
            throw new ParamNotFoundException("Given ID is not valid");
        }
        News returnModel = this.entityToModel(optional.get());
        return returnModel;
    }

    @Override
    public News save(News news) {
        return null;
    }

    @Override
    public News getDetails(Long id) {
        return this.findById(id);
    }

    private NewsEntity modelToEntity(News news){
        NewsEntity returnEntity = NewsEntity.builder()
                .id(news.getId())
                .name(news.getName())
                .content(news.getContent())
                .image(news.getImage())
                .categoryId(news.getCategoryId())
                .type(news.getType())
                .build();
        return returnEntity;
    }

    private News entityToModel(NewsEntity newsEntity){
        News returnModel = News.builder()
                .id(newsEntity.getId())
                .name(newsEntity.getName())
                .content(newsEntity.getContent())
                .image(newsEntity.getImage())
                .categoryId(newsEntity.getCategoryId())
                .type(newsEntity.getType())
                .build();
        return returnModel;
    }
}
