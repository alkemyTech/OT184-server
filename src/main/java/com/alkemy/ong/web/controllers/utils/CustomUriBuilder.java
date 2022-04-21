package com.alkemy.ong.web.controllers.utils;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.StringJoiner;

@Component
public class CustomUriBuilder {
    public void addLinkHeaderOnPagedResourceRetrieval(
            UriComponentsBuilder uriBuilder,
            HttpServletResponse response,
            Page page) {

        int pageNumber = page.getNumber();
        int totalPages = page.getTotalPages();
        String resourceName = page.getContent().get(0).getClass().getSimpleName().toLowerCase();

        uriBuilder.path(resourceName);

        StringJoiner linkHeader = new StringJoiner(", ");

        if (hasNextPage(pageNumber, totalPages)){
            String uriForNextPage = constructNextPageUri(uriBuilder, pageNumber);
            linkHeader.add(createLinkHeader(uriForNextPage, "next"));
        }
        if (hasPreviousPage(pageNumber)) {
            String uriForPrevPage = constructPrevPageUri(uriBuilder, pageNumber);
            linkHeader.add(createLinkHeader(uriForPrevPage, "prev"));
        }

        response.addHeader("Link", linkHeader.toString());
    }
    String constructPrevPageUri(final UriComponentsBuilder uriBuilder, final int page) {
        return uriBuilder.replaceQueryParam("page", page - 1).build().encode().toUriString();
    }
    String constructNextPageUri(UriComponentsBuilder uriBuilder, int page) {
        return uriBuilder.replaceQueryParam("page", page + 1)
                .build()
                .encode()
                .toUriString();
    }
    boolean hasNextPage(final int page, final int totalPages) {
        return page < totalPages - 1;
    }
    boolean hasPreviousPage(final int page) {
        return page > 0;
    }
    public static String createLinkHeader(final String uri, final String rel) {
        return "<" + uri + ">; rel=\"" + rel + "\"";
    }
}
