package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entity.TestimonialEntity;
import com.alkemy.ong.data.repository.TestimonialRepository;
import com.alkemy.ong.domain.testimonials.Testimonial;
import com.alkemy.ong.domain.testimonials.TestimonialGateway;
import org.springframework.stereotype.Component;

@Component
public class ImplementationTestimonialGateway implements TestimonialGateway {

    private TestimonialRepository testimonialRepository;

    public ImplementationTestimonialGateway(TestimonialRepository testimonialRepository) {
        this.testimonialRepository = testimonialRepository;
    }

    @Override
    public Testimonial create(Testimonial testimonial) {
        TestimonialEntity entity = toEntity(testimonial);
        return toModel(testimonialRepository.save(entity));
    }

    @Override
    public Testimonial update(Long id, Testimonial testimonial) {
        TestimonialEntity entity = testimonialRepository.findById(id).orElseThrow();
        entity.setName(testimonial.getName());
        entity.setImage(testimonial.getImage());
        entity.setContent(testimonial.getContent());
        return toModel(testimonialRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        TestimonialEntity entity = testimonialRepository.findById(id).orElseThrow();
        entity.setDeleted(true);
        testimonialRepository.save(entity);
    }

    private Testimonial toModel(TestimonialEntity testimonialEntity) {
        return Testimonial.builder()
                .id(testimonialEntity.getId())
                .name(testimonialEntity.getName())
                .image(testimonialEntity.getImage())
                .content(testimonialEntity.getContent())
                .build();
    }

    private TestimonialEntity toEntity(Testimonial testimonial) {
        return TestimonialEntity.builder()
                .name(testimonial.getName())
                .content(testimonial.getContent())
                .image(testimonial.getImage())
                .build();
    }
}

