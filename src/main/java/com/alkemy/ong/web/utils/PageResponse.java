package com.alkemy.ong.web.utils;

import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T extends Object> {

    private List<T> content;
    private String nextPage;
    private String previousPage;

    public PageResponse(List<T> content, String url, int page, int size) {
        this.content = content;
        this.nextPage = (content.size() < size) ? "" : url + "?page=" + (page + 1);
        this.previousPage = ((page > 0) ? (url + "?page=" + (page - 1)) : "");
    }
}
