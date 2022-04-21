package com.alkemy.ong.web.controllers;

import com.alkemy.ong.domain.slide.Slide;
import com.alkemy.ong.domain.slide.SlideService;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long id){
        slideService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @Data
    @Builder
    private static class SlideDTO{

    }

    private SlideDTO toDTO(Slide slide){
        return SlideDTO.builder().build();
    }

}
