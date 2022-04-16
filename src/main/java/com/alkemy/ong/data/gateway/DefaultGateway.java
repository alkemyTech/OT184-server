package com.alkemy.ong.data.gateway;

import com.alkemy.ong.domain.gateway.NewsGateway;
import com.alkemy.ong.web.controller.dto.NewsDTO;

public class DefaultGateway implements NewsGateway {
    @Override
    public NewsDTO save(NewsDTO newsDTO) {

        return new NewsDTO();
    }
}
