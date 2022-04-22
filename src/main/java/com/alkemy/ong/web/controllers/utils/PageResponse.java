package com.alkemy.ong.web.controllers.utils;

import com.alkemy.ong.domain.news.News;
import com.alkemy.ong.domain.news.PageNews;
import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T> {

    private List<News> content;
    private String nextPage;
    private String previousPage;

    public PageResponse(PageNews pageNews, String path, int pageNumber, int pageSize) {
        this.content = pageNews.getContent();
        this.nextPage = (content.size() < pageSize) || pageNumber == pageNews.getTotalPages()
                ? "" :
                path + "?page=" + (pageNumber + 1);
        this.previousPage = (pageNumber <= 1) || (content.size() == 0)
                ? ""
                : path + "?page=" + (pageNumber - 1);

    }
}
