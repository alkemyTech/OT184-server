package com.alkemy.ong.domain.testimonials;

import java.util.List;

public interface TestimonialGateway {

    Testimonial create(Testimonial testimonial);

    Testimonial update(Long id, Testimonial testimonial);

    void delete(Long id);

    List<Testimonial> listByPage(int page, int size);

}
