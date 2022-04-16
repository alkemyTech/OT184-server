package com.alkemy.ong.web.controller;

import com.alkemy.ong.domain.model.News;
import com.alkemy.ong.domain.service.NewsService;
import com.alkemy.ong.web.controller.dto.NewsDTO;
import com.alkemy.ong.web.controller.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("news")
public class NewsController {

    @Autowired
    NewsService newsService;
    @Autowired
    NewsMapper newsMapper;

    @PostMapping
    public ResponseEntity<NewsDTO> save(@Valid @RequestBody NewsDTO newsDTO){
        News returnNews =  newsService.save(newsMapper.DTOToModel(newsDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(newsMapper.modelToDTO(returnNews));
    }
}
