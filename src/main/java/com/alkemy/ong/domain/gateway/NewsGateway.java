package com.alkemy.ong.domain.gateway;

import com.alkemy.ong.web.controller.dto.NewsDTO;

public interface NewsGateway {
    void save(NewsDTO newsDTO);
}
