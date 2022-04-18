package com.alkemy.ong.domain.gateway;

import com.alkemy.ong.domain.model.News;
import org.springframework.stereotype.Service;

@Service
public interface NewsGateway {

    News getDetails(Long id);
}
