package com.alkemy.ong.web.controller;

import com.alkemy.ong.domain.service.NewsService;
import com.alkemy.ong.web.controller.mapper.NewsDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("news")
public class NewsController {

    @Autowired
    NewsService newsService;
    @Autowired
    NewsDTOMapper newsDTOMapper;


}
