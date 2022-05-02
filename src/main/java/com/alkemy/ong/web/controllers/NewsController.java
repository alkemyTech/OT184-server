package com.alkemy.ong.web.controllers;

import com.alkemy.ong.domain.comments.Comment;
import com.alkemy.ong.domain.comments.CommentService;
import com.alkemy.ong.domain.exceptions.ResourceNotFoundException;
import com.alkemy.ong.domain.news.News;
import com.alkemy.ong.domain.news.NewsService;
import com.alkemy.ong.domain.utils.PageModel;
import com.alkemy.ong.web.controllers.utils.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Builder;
import lombok.Data;
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
    private final CommentService commentService;
    public NewsController(NewsService newsService, CommentService commentService){
        this.newsService = newsService;
        this.commentService = commentService;
    }

    @Operation(summary = "Get news by ID",
            description = "Get news details by ID")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success, news found",
                            content = @Content(
                                    mediaType = "JSON Value",
                                    schema = @Schema(implementation = NewsController.NewsDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "News not found",
                            content = @Content(
                                    mediaType = "JSON Value",
                                    schema = @Schema(implementation = ResourceNotFoundException.class),
                                    examples = @ExampleObject(
                                            name = "404",
                                            summary = "Client Error 404",
                                            description = "Bad Request, ID is not found",
                                            value = ("Error, ID is not found.")
                                    )
                            ))
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> getDetails(@PathVariable Long id){
        NewsDTO returnDTO = this.toDTO(newsService.getDetails(id));
        return ResponseEntity.status(HttpStatus.OK).body(returnDTO);
    }

    @Operation(summary = "Create news",
            description = "Create news with all details")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "News created"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    examples = @ExampleObject(
                                            name = "400",
                                            summary = "Client Error 400",
                                            description = "The request of the save news is no correct",
                                            value = ("Bad request, news not saved")
                                    )
                            ))}
    )
    @PostMapping
    public ResponseEntity<NewsDTO> save(@Valid @RequestBody NewsDTO newsDTO){
        News returnModel =  newsService.save(this.toModel(newsDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.toDTO(returnModel));
    }

    @Operation(summary = "Delete news by ID",
            description = "Soft news by ID of the specific category")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "No content, news deleted"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "News not found",
                            content = @Content(
                                    mediaType = "JSON Value",
                                    schema = @Schema(implementation = ResourceNotFoundException.class),
                                    examples = @ExampleObject(
                                            name = "404",
                                            summary = "Client Error 404",
                                            description = "The request id is not found",
                                            value = ("Error, ID is not found.")
                                    )
                            ))
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        newsService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Get all news by page",
            description = "Get all news by pages of 10")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "News found",
                            content = @Content(
                                    mediaType = "JSON Value",
                                    schema = @Schema(implementation = NewsController.NewsDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content(
                                    examples = @ExampleObject(
                                            name = "400",
                                            summary = "Client Error 400",
                                            description = "Bad Request, site not correct",
                                            value = ("Bad request, URL doesn't exist")
                                    )
                            ))
            }
    )
    @GetMapping
    public ResponseEntity<PageResponse<NewsDTO>> findByPage(@Valid @RequestParam("page") int pageNumber) {
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

    @Operation(summary = "Update news by ID",
            description = "Update all details of the specific news ")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success, news updated"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "News not found",
                            content = @Content(
                                    mediaType = "JSON Value",
                                    schema = @Schema(implementation = ResourceNotFoundException.class),
                                    examples = @ExampleObject(
                                            name = "404",
                                            summary = "Client Error 400",
                                            description = "The request id is not found",
                                            value = ("Error, ID is not found.")
                                    )
                            ))
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<NewsDTO> update(@PathVariable Long id,@Valid @RequestBody NewsDTO newsDTO){
        return ResponseEntity.ok().body(toDTO(newsService.update(this.toModel(newsDTO),id)));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentController.CommentDTO>> findAllByNewsId(@PathVariable Long id){
        return ResponseEntity.ok().body(commentService.findAllByNewsId(id).stream().map(this::toDto).collect(toList()));
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


