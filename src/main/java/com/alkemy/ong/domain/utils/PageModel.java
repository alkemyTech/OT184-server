package com.alkemy.ong.domain.utils;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageModel<T> {
    private List<T> content;
    private String nextPage;
    private String previousPage;
    private int totalPages;
}
