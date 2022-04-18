package com.alkemy.ong.data.gateway;

import com.alkemy.ong.domain.gateway.NewsGateway;
import com.alkemy.ong.domain.model.News;
import org.springframework.stereotype.Component;

@Component
public class DefaultGateway implements NewsGateway {


    @Override
    public void delete(Long id) {
        
    }

    @Override
    public News findById(Long id) {
        return null;
    }
}
