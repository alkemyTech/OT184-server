package com.alkemy.ong.web.controllers;

import com.alkemy.ong.data.entities.NewsEntity;
import com.alkemy.ong.domain.news.News;
import com.alkemy.ong.domain.news.NewsService;
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
import java.util.StringJoiner;


@RestController
@RequestMapping("news")
public class NewsController {

    private final NewsService newsService;
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

    void addLinkHeaderOnPagedResourceRetrieval(
            UriComponentsBuilder uriBuilder, HttpServletResponse response,
            Class clazz, int page, int totalPages, int size ){

        String resourceName = clazz.getSimpleName().toString().toLowerCase();
        uriBuilder.path( "" + resourceName );

        // ...
        StringJoiner linkHeader = new StringJoiner(", ");
        if (hasNextPage(page, totalPages)){
            String uriForNextPage = constructNextPageUri(uriBuilder, page, size);
            linkHeader.add(createLinkHeader(uriForNextPage, "next"));
            String uriForPrevPage = constructPrevPageUri(uriBuilder, page, size);
            linkHeader.add(createLinkHeader(uriForPrevPage, "prev"));
        }
        response.addHeader("Link", linkHeader.toString());

    }
    String constructPrevPageUri(final UriComponentsBuilder uriBuilder, final int page, final int size) {
        return uriBuilder.replaceQueryParam("page", page - 1).replaceQueryParam("size", size).build().encode().toUriString();
    }
    String constructNextPageUri(UriComponentsBuilder uriBuilder, int page, int size) {
        return uriBuilder.replaceQueryParam("page", page + 1)
                .replaceQueryParam("size", size)
                .build()
                .encode()
                .toUriString();
    }
    boolean hasNextPage(final int page, final int totalPages) {
        return page < totalPages - 1;
    }

    boolean hasPreviousPage(final int page) {
        return page > 0;
    }

    boolean hasFirstPage(final int page) {
        return hasPreviousPage(page);
    }

    boolean hasLastPage(final int page, final int totalPages) {
        return totalPages > 1 && hasNextPage(page, totalPages);
    }
    public static String createLinkHeader(final String uri, final String rel) {
        return "<" + uri + ">; rel=\"" + rel + "\"";
    }
    @GetMapping
    public ResponseEntity<Page<NewsEntity>> getAll(@RequestParam("page") int page,
                                                   UriComponentsBuilder uriBuilder,
                                                   HttpServletResponse response){
        Page<NewsEntity> p = newsService.getAll();
        addLinkHeaderOnPagedResourceRetrieval(uriBuilder,response,NewsEntity.class,2,150,10);


        return ResponseEntity.status(HttpStatus.OK).body(p);
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



