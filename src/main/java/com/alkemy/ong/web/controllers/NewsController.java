package com.alkemy.ong.web.controllers;

import com.alkemy.ong.data.entities.NewsEntity;
import com.alkemy.ong.domain.news.News;
import com.alkemy.ong.domain.news.NewsService;
import com.alkemy.ong.web.controllers.utils.CustomUriBuilder;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;


@RestController
@RequestMapping("news")
public class NewsController {

    private final NewsService newsService;
    private final CustomUriBuilder customUriBuilder;
    public NewsController(NewsService newsService, CustomUriBuilder customUriBuilder){
        this.newsService = newsService;
        this.customUriBuilder = customUriBuilder;
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
    public ResponseEntity<Page<NewsEntity>> getAllPageable(@Valid @RequestParam("page") int pageNumber,
                                                   UriComponentsBuilder uriBuilder,
                                                   HttpServletResponse response){
        // aca recibo un page de news
        Page<News> page = newsService.getAllPageable(pageNumber);
        page.getContent(); // lista de news, convertir a dto y devolver
        customUriBuilder.addLinkHeaderOnPagedResourceRetrieval(uriBuilder, response, page);

        return ResponseEntity.status(HttpStatus.OK).body(page);
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

    private NewsDTO toDTO(News news){
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

    private News toModel(NewsDTO newsDTO){
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


