package com.alkemy.ong.web.controllers;

import com.alkemy.ong.domain.testimonials.Testimonial;
import com.alkemy.ong.domain.testimonials.TestimonialService;
import com.alkemy.ong.domain.utils.PageModel;
import com.alkemy.ong.web.controllers.utils.PageResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/testimonials")
public class TestimonialController {

    private final TestimonialService testimonialService;

    public TestimonialController(TestimonialService testimonialService) {
        this.testimonialService = testimonialService;
    }

    @GetMapping
    public ResponseEntity<PageResponse<TestimonialDTO>> getTestimonialPage(@RequestParam("page") int pageNumber) {
        PageModel<Testimonial> page = testimonialService.findByPage(pageNumber);
        String path = "/testimonials";
        PageResponse response = PageResponse.builder()
                .content(page.getContent()
                        .stream()
                        .map(this::toDto)
                        .collect(toList()))
                .build();
        response.setResponse(path, pageNumber, page.getTotalPages(), page.isFirst(), page.isLast());
        return new ResponseEntity<>(response, HttpStatus.OK);
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
