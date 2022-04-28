package com.alkemy.ong.domain.slide;

import com.alkemy.ong.domain.news.News;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlideService {

    private final SlideGateway slideGateway;

    public SlideService(SlideGateway slideGateway){
        this.slideGateway = slideGateway;
    }

    public Slide findById(Long id){return slideGateway.findById(id);}

    public void delete(Long id){slideGateway.delete(id);}

    public List<Slide> findAll() {return slideGateway.findAll();}

    public Slide save(Slide slide) { return slideGateway.save(slide); }

}
