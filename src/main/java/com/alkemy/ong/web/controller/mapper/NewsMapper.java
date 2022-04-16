package com.alkemy.ong.web.controller.mapper;

import com.alkemy.ong.data.entity.NewsEntity;
import com.alkemy.ong.domain.model.News;
import com.alkemy.ong.web.controller.dto.NewsDTO;

public class NewsMapper {

    public NewsDTO modelToDTO(News news){
        NewsDTO returnDTO = NewsDTO.builder()
                .id(news.getId())
                .name(news.getName())
                .content(news.getContent())
                .image(news.getImage())
                .categoryId(news.getCategoryId())
                .type(news.getType())
                .build();
        return returnDTO;
    }

    public News DTOToModel(NewsDTO newsDTO){
        News returnModel = News.builder()
                .id(newsDTO.getId())
                .name(newsDTO.getName())
                .content(newsDTO.getContent())
                .image(newsDTO.getImage())
                .categoryId(newsDTO.getCategoryId())
                .type(newsDTO.getType())
                .build();
        return returnModel;
    }
}
