package com.alkemy.ong.web.controllers;

import com.alkemy.ong.domain.testimonials.Testimonial;
import com.alkemy.ong.domain.testimonials.TestimonialService;
import com.alkemy.ong.web.exceptions.ArgumentException;
import com.alkemy.ong.web.utils.PageResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/testimonials")
public class TestimonialController {

    private final TestimonialService testimonialService;

    public TestimonialController(TestimonialService testimonialService) {
        this.testimonialService = testimonialService;
    }

    @GetMapping
    public ResponseEntity<PageResponse<TestimonialDTO>> lisAllByPage(@RequestParam(name = "page") Integer page) {
        if (page < 0)
            throw new ArgumentException(page).pageNumberError();
        List<TestimonialDTO> testimonialDTOS = testimonialService.listByPage(page, 10)
                .stream()
                .map(p -> toDto(p))
                .collect(Collectors.toList());
        PageResponse<TestimonialDTO> pageResponse = new PageResponse<>(testimonialDTOS, "/testimonials", page, 10);
        return new ResponseEntity<>(pageResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TestimonialDTO> save(@RequestBody TestimonialDTO dto) {
        Testimonial testimonial = testimonialService.save(toDomain(dto));
        return new ResponseEntity<>(toDto(testimonial), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestimonialDTO> update(@PathVariable Long id, @RequestBody TestimonialDTO dto) {
        Testimonial testimonial = testimonialService.update(id, toDomain(dto));
        return new ResponseEntity<>(toDto(testimonial), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        testimonialService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
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
