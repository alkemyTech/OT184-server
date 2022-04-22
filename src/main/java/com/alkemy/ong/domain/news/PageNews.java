package com.alkemy.ong.domain.news;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageNews {
    private List<News> content;
    private String nextPage;
    private String previousPage;
    private int totalPages;
}
