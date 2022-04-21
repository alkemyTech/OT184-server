package com.alkemy.ong.data.gateways;

import com.alkemy.ong.domain.slide.Slide;
import com.alkemy.ong.domain.slide.SlideGateway;
import org.springframework.stereotype.Component;

@Component
public class DefaultSlideGateway implements SlideGateway {


    @Override
    public Slide findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
