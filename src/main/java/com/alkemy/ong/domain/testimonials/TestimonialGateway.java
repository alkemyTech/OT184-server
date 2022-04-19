package com.alkemy.ong.domain.testimonials;

import com.alkemy.ong.domain.testimonials.Testimonial;

public interface TestimonialGateway {
    Testimonial create(Testimonial testimonial);

    Testimonial update(Long id, Testimonial testimonial);

    void delete(Long id);
}
