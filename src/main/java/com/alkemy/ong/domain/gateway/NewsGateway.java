package com.alkemy.ong.domain.gateway;

import com.alkemy.ong.domain.model.News;
import org.springframework.stereotype.Service;

@Service
public interface NewsGateway {

    void delete(Long id);
    News findById(Long id);

}
