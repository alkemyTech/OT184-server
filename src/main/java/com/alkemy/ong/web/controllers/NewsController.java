package com.alkemy.ong.web.controllers;

import com.alkemy.ong.domain.news.News;
import com.alkemy.ong.domain.news.NewsService;
import com.alkemy.ong.domain.news.PageNews;
import com.alkemy.ong.web.controllers.utils.PageResponse;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

import static java.util.stream.Collectors.toList;


@RestController
@RequestMapping("news")
public class NewsController {

    private final NewsService newsService;
    @Value("${spring.application.pageSize}")
    private int pageSize;

    public NewsController(NewsService newsService){
        this.newsService = newsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> getDetails(@PathVariable Long id){
        NewsDTO returnDTO = this.toDTO(newsService.getDetails(id));
        return ResponseEntity.status(HttpStatus.OK).body(returnDTO);
    }

    @PostMapping
    public ResponseEntity<NewsDTO> save(@Valid @RequestBody NewsDTO newsDTO){
        News returnModel =  newsService.save(this.toModel(newsDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.toDTO(returnModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        newsService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



    @GetMapping
    public ResponseEntity<PageResponse<NewsDTO>> findByPage(@Valid @RequestParam("page") int pageNumber){
        PageNewsDTO pageNewsDTO = pageToDTO(newsService.findByPage(pageNumber));
        String path = "/news";
        PageResponse<NewsDTO> pageResponse = new PageResponse<>(pageNewsDTO.getContent()
                ,path
                ,pageNumber
                ,pageSize
                ,pageNewsDTO.getTotalPages());
        return ResponseEntity.status(HttpStatus.OK).body(pageResponse);
    }

    @Data
    @Builder
    private static class NewsDTO {
        private Long id;
        @NotEmpty(message = "The field Name must not be empty")
        private String name;
        @NotEmpty (message = "The field Content must not be empty")
        private String content;
        @NotEmpty(message = "The field Image must not be empty")
        private String image;
        private Long categoryId;
        private String type;
    }

    @Data
    @Builder
    private static class PageNewsDTO {
        private List<NewsDTO> content;
        private int totalPages;
    }

    private NewsDTO toDTO(News news){
        return NewsDTO.builder()
                .id(news.getId())
                .name(news.getName())
                .content(news.getContent())
                .image(news.getImage())
                .categoryId(news.getCategoryId())
                .type(news.getType())
                .build();
    }

    private News toModel(NewsDTO newsDTO){
        return News.builder()
                .id(newsDTO.getId())
                .name(newsDTO.getName())
                .content(newsDTO.getContent())
                .image(newsDTO.getImage())
                .categoryId(newsDTO.getCategoryId())
                .type(newsDTO.getType())
                .build();
    }

    private PageNewsDTO pageToDTO(PageNews pageNews){
        return PageNewsDTO.builder()
                .content(pageNews.getContent()
                        .stream()
                        .map(this::toDTO)
                        .collect(toList()))
                .totalPages(pageNews.getTotalPages())
                .build();
    }
}


