package com.alkemy.ong.domain.services;

import com.alkemy.ong.domain.Testimonial;
import com.alkemy.ong.domain.gateways.TestimonialGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestimonialService {

    @Autowired
    private TestimonialGateway testimonialGateway;

    public Testimonial save(Testimonial testimonial) {
        return testimonialGateway.create(testimonial);
    }
}
