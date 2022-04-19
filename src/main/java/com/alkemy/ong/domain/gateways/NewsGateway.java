package com.alkemy.ong.domain.gateways;

import com.alkemy.ong.domain.models.News;
import org.springframework.stereotype.Service;

@Service
public interface NewsGateway {

    News getDetails(Long id);
}
