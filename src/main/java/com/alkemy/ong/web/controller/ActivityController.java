package com.alkemy.ong.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activities")
public class ActivityController {

  @PostMapping
  public ResponseEntity<ActivityDto> save(@RequestBody ActivityDto activity) {
    ActivityDto saveActivity = activityService.save(activity);
    return ResponseEntity.status(HttpStatus.CREATED).body(saveActivity)
  }
}
