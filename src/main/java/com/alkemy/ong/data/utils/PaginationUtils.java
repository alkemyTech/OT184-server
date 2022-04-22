package com.alkemy.ong.data.utils;

import com.alkemy.ong.domain.utils.PageModel;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PaginationUtils {

    public PageModel toModel(Page page){
        return PageModel.builder()
                .content(page.getContent())
                .pageSize(page.getSize())
                .totalPages(page.getTotalPages())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}
