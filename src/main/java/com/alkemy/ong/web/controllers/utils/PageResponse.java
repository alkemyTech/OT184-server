package com.alkemy.ong.web.controllers.utils;

import lombok.Data;

import java.util.List;


@Data
public class PageResponse<T> {

    private List<T> content;
    private String nextPage;
    private String previousPage;

    public PageResponse(List<T> content, String path, int pageNumber, int pageSize, int totalPages) {
        if(pageNumber > totalPages){
            throw new IllegalArgumentException("Incorrect index");
        }
        this.content = content;
        this.nextPage = (content.size() < pageSize) || pageNumber == totalPages
                ? "" :
                path + "?page=" + (pageNumber + 1);
        this.previousPage = (pageNumber < 1) || (content.size() == 0)
                ? ""
                : path + "?page=" + (pageNumber - 1);

    }
}
