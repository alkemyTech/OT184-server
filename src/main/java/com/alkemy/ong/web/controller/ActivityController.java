package com.alkemy.ong.web.controller;

import com.alkemy.ong.domain.model.Activity;
import com.alkemy.ong.domain.service.ActivityService;
import com.alkemy.ong.web.dto.ActivityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activities")
public class ActivityController {

  @Autowired
  ActivityService activityService;

  @PostMapping
  public ResponseEntity<ActivityDto> save(@RequestBody ActivityDto activityDto) {
    Activity saveActivity = activityService.save(
        Activity
            .builder()
            .name(activityDto.getName())
            .content(activityDto.getContent())
            .image(activityDto.getImage())
            .build()
    );

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(
            ActivityDto.builder()
                .name(saveActivity.getName())
                .content(saveActivity.getContent())
                .image(saveActivity.getImage())
                .build()
        );
  }
}
