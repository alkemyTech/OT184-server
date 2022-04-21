package com.alkemy.ong.domain.slide;

import org.springframework.stereotype.Service;

@Service
public class SlideService {

    private final SlideGateway slideGateway;

    public SlideService(SlideGateway slideGateway){
        this.slideGateway = slideGateway;
    }

    public Slide findById(Long id){return slideGateway.findById(id);}

    public void delete(Long id){slideGateway.delete(id);}

}
