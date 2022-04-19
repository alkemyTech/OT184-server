package com.alkemy.ong.web;

import com.alkemy.ong.domain.testimonials.Testimonial;
import com.alkemy.ong.domain.testimonials.TestimonialService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/testimonials")
public class TestimonialController {

    @Autowired
    private final TestimonialService testimonialService;

    public TestimonialController(TestimonialService testimonialService) {
        this.testimonialService = testimonialService;
    }

    @PostMapping("/")
    public ResponseEntity<TestimonialDTO> save(@RequestBody TestimonialDTO dto) {
        Testimonial testimonial = testimonialService.save(toDomain(dto));
        return new ResponseEntity<>(toDto(testimonial), HttpStatus.CREATED);
    }

    private Testimonial toDomain(TestimonialDTO dto) {
        return Testimonial.builder()
                .id(dto.getId())
                .name(dto.getName())
                .image(dto.getImage())
                .content(dto.getContent())
                .build();
    }

    private TestimonialDTO toDto(Testimonial testimonial) {
        return TestimonialDTO.builder()
                .id(testimonial.getId())
                .name(testimonial.getName())
                .image(testimonial.getImage())
                .content(testimonial.getContent())
                .build();
    }

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TestimonialDTO {
        private Long id;
        private String name;
        private String image;
        private String content;
    }

}
