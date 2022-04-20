package com.alkemy.ong.domain.testimonials;


public interface TestimonialGateway {
    Testimonial create(Testimonial testimonial);

    Testimonial update(Long id, Testimonial testimonial);

    void delete(Long id);
}
