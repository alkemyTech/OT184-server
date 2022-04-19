package com.alkemy.ong.web.controller;
import com.alkemy.ong.domain.models.News;
import com.alkemy.ong.domain.services.NewsService;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;


@RestController
@RequestMapping("news")
public class NewsController {

    @Autowired
    NewsService newsService;

    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> getDetails(@PathVariable Long id){
        NewsDTO returnDTO = this.modelToDTO(newsService.getDetails(id));
        return ResponseEntity.status(HttpStatus.FOUND).body(returnDTO);
    }

    @Data
    @Builder
    private class NewsDTO {
        private Long id;
        @NotEmpty(message = "The field Name must not be empty")
        private String name;
        @NotEmpty (message = "The field Content must not be empty")
        private String content;
        @NotEmpty(message = "The field Image must not be empty")
        private String image;
        //private CategoryEntity category;
        private Long categoryId;
        private String type;
    }

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



