package com.alkemy.ong.web.controllers;

import com.alkemy.ong.domain.comments.Comment;
import com.alkemy.ong.domain.comments.CommentService;
import com.alkemy.ong.domain.news.News;
import com.alkemy.ong.domain.news.NewsService;
import com.alkemy.ong.domain.utils.PageModel;
import com.alkemy.ong.web.controllers.utils.PageResponse;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

import static java.util.stream.Collectors.toList;


@RestController
@RequestMapping("news")
public class NewsController {

    private final NewsService newsService;
    private final CommentService commentService;
    public NewsController(NewsService newsService, CommentService commentService){
        this.newsService = newsService;
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> getDetails(@PathVariable Long id){
        NewsDTO returnDTO = this.toDTO(newsService.getDetails(id));
        return ResponseEntity.status(HttpStatus.OK).body(returnDTO);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentController.CommentDTO>> findAllByNewsId(@PathVariable Long id){
        return ResponseEntity.ok().body(commentService.findAllByNewsId(id).stream().map(this::toDto).collect(toList()));
    }
    @GetMapping
    public ResponseEntity<PageResponse<NewsDTO>> findAll(@Valid @RequestParam(value = "page",defaultValue = "0", required = false) int pageNumber) {
        PageModel<News> page = newsService.findByPage(pageNumber);
        String path = "/news";
        PageResponse response = PageResponse.builder()
                .content(page.getContent()
                        .stream()
                        .map(this::toDTO)
                        .collect(toList()))
                .build();
        response.setResponse(path,pageNumber,page.getTotalPages(),page.isFirst(),page.isLast());
        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<NewsDTO> save(@Valid @RequestBody NewsDTO newsDTO){
        News returnModel =  newsService.save(this.toModel(newsDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.toDTO(returnModel));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        newsService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<NewsDTO> update(@PathVariable Long id,@Valid @RequestBody NewsDTO newsDTO){
        return ResponseEntity.ok().body(toDTO(newsService.update(this.toModel(newsDTO),id)));
    }

    private CommentController.CommentDTO toDto(Comment comment) {
        return CommentController.CommentDTO.builder()
                .id(comment.getId())
                .userId(comment.getUserId())
                .body(comment.getBody())
                .newsId(comment.getNewsId())
                .build();
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
}


