package com.alkemy.ong.web.controller;
import com.alkemy.ong.domain.service.NewsService;
import com.alkemy.ong.web.controller.dto.NewsDTO;
import com.alkemy.ong.web.controller.mapper.NewsDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("news")
public class NewsController {

    @Autowired
    NewsService newsService;
    @Autowired
    NewsDTOMapper newsDTOMapper;

    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> getDetails(@PathVariable Long id){
        NewsDTO returnDTO = newsDTOMapper.modelToDTO(newsService.getDetails(id));
        return ResponseEntity.status(HttpStatus.FOUND).body(returnDTO);
    }
}
