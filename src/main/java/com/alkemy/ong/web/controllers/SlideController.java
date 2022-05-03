package com.alkemy.ong.web.controllers;


import com.alkemy.ong.domain.slide.Slide;
import com.alkemy.ong.domain.slide.SlideService;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/slides")
public class SlideController {

    private final SlideService slideService;

    public SlideController(SlideService slideService){
        this.slideService = slideService;
    }

    @GetMapping("/{id}")
    private ResponseEntity<SlideDTO> getSlideById(@PathVariable Long id){
        Slide slide = slideService.findById(id);
        SlideDTO slideDTO = toDTO(slide);
        return ResponseEntity.ok().body(slideDTO);

    }

    @GetMapping
    public ResponseEntity<List<SlideBasicDTO>> findAll() {
        return ResponseEntity.ok().body(toListDto(slideService.findAll()));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long id){
        slideService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @Data
    @Builder
    private static class SlideDTO{
        private Long id;
        private String imageUrl;
        private String text;
        private Integer order;
        private OrganizationController.OrganizationPublicDTO organizationPublicDTO;
    }

    @Data
    @Builder
    private static class SlideBasicDTO{
        private String imageUrl;
        private Integer order;
    }
    private SlideBasicDTO toBasicDTO(Slide slide){

        return SlideBasicDTO.builder()
                .imageUrl(slide.getImageUrl())
                .order(slide.getOrder())
                .build();
    }

    private SlideDTO toDTO(Slide slide){

        return SlideDTO.builder()
                .id(slide.getId())
                .imageUrl(slide.getImageUrl())
                .text(slide.getText())
                .order(slide.getOrder())
                .organizationPublicDTO(OrganizationController.toDTO(slide.getOrganization()))
                .build();
    }

    private List<SlideBasicDTO> toListDto(List<Slide> slides) {
        return slides.stream()
                .map(this::toBasicDTO)
                .collect(toList());
    }

    @Data
    @Builder
    public static class SlideOrgDTO{
        private Long id;
        private String imageUrl;
        private String text;
        private Integer order;
    }

    public static SlideOrgDTO toOrgDTO(Slide slide){
        return SlideOrgDTO.builder()
                .id(slide.getId())
                .imageUrl(slide.getImageUrl())
                .text(slide.getText())
                .order(slide.getOrder())
                .build();
    }
}
