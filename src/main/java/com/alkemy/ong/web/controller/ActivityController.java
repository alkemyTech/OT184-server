package com.alkemy.ong.web.controller;

import com.alkemy.ong.domain.activity.Activity;
import com.alkemy.ong.domain.activity.ActivityService;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/activities")
public class ActivityController {

  private final ActivityService activityService;

  @Autowired
  public ActivityController(ActivityService activityService) {
    this.activityService = activityService;
  }

  @PostMapping
  public ResponseEntity<ActivityDto> save(@Valid @RequestBody ActivityDto activityDto) {
    Activity saveActivity = activityService.save(activityDto2Activity(activityDto));

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(activity2Dto(saveActivity));
  }

  private Activity activityDto2Activity(ActivityDto activityDto) {
    return Activity
        .builder()
        .name(activityDto.getName())
        .content(activityDto.getContent())
        .image(activityDto.getImage())
        .build();
  }

  private ActivityDto activity2Dto(Activity saveActivity) {
    return ActivityDto.builder()
        .name(saveActivity.getName())
        .content(saveActivity.getContent())
        .image(saveActivity.getImage())
        .build();
  }

  @Builder
  @Data
  public static class ActivityDto {
    @NotBlank(message = "Name is required")
    String name;

    @NotBlank(message = "Content is required")
    String content;

    @NotBlank(message = "Image is required")
    String image;
  }

}
