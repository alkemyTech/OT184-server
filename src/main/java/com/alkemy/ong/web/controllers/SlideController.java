package com.alkemy.ong.web.controllers;

import com.alkemy.ong.data.entities.OrganizationEntity;
import com.alkemy.ong.domain.slide.Slide;
import com.alkemy.ong.domain.slide.SlideService;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/slide")
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

    @Data
    @Builder
    private static class SlideDTO{
        private Long id;
        private String imageUrl;
        private String text;
        private Integer order;
        private OrganizationEntity organization;

    }

    private SlideDTO toDTO(Slide slide){
        return SlideDTO.builder().build();
    }

}
