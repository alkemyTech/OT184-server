package com.alkemy.ong.domain.gateway;

import com.alkemy.ong.web.controller.dto.NewsDTO;

public interface NewsGateway {
    NewsDTO save(NewsDTO newsDTO);
}
