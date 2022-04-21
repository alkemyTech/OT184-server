package com.alkemy.ong.web.controllers.utils;

import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T extends Object> {

    private List<T> content;
    private String nextPage;
    private String previousPage;

    public PageResponse(List<T> content, String path, int pageNumber, int size) {
        this.content = content;
        this.nextPage = (content.size() < size) ? "" : path + "?page=" + (pageNumber + 1);
        this.previousPage = (pageNumber <= 1) || (content.size() == 0)
                ? ""
                : path + "?page=" + (pageNumber - 1);
    }
}
