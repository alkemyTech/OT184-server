package com.alkemy.ong.data.gateway;

import com.alkemy.ong.data.entity.NewsEntity;
import com.alkemy.ong.data.exception.ParamNotFoundException;
import com.alkemy.ong.data.mapper.NewsModelMapper;
import com.alkemy.ong.data.repository.NewsRepository;
import com.alkemy.ong.domain.gateway.NewsGateway;
import com.alkemy.ong.domain.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DefaultGateway implements NewsGateway {

    @Autowired
    NewsRepository newsRepository;
    @Autowired
    NewsModelMapper newsModelMapper;

    public News findById(Long id) {
        Optional<NewsEntity> optional = newsRepository.findById(id);
        if(!optional.isPresent()){
            throw new ParamNotFoundException("Given ID is not valid");
        }
        News returnModel = newsModelMapper.entityToModel(optional.get());
        return returnModel;
    }

    @Override
    public News getDetails(Long id) {
        return this.findById(id);
    }
}
