package com.alkemy.ong.web.controllers;

import com.alkemy.ong.domain.comments.Comment;
import com.alkemy.ong.domain.comments.CommentService;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping
    public ResponseEntity<CommentDTO> agregar(@Valid @RequestBody CommentDTO dto) {
        Comment comment = commentService.create(toDomain(dto));
        return new ResponseEntity<>(toDto(comment), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private Comment toDomain(CommentDTO dto) {
        return Comment.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .body(dto.getBody())
                .newsId(dto.getNewsId())
                .build();
    }

    private CommentDTO toDto(Comment comment) {
        return CommentDTO.builder()
                .id(comment.getId())
                .userId(comment.getUserId())
                .body(comment.getBody())
                .newsId(comment.getNewsId())
                .build();
    }

    @Builder
    @Data
    public static class CommentDTO {
        private Long id;
        @NotNull(message = "El id de usuario es obligatorio")
        private Long userId;
        @NotEmpty(message = "El comentario es obligatorio")
        private String body;
        @NotNull(message = "El id de novedad es obligatorio")
        private Long newsId;
    }
}
