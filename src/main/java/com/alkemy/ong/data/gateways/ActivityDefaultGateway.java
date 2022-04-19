package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.ActivityEntity;
import com.alkemy.ong.data.repositories.ActivityRepository;
import com.alkemy.ong.domain.activity.Activity;
import com.alkemy.ong.domain.activity.ActivityGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivityDefaultGateway implements ActivityGateway {
  private final ActivityRepository activityRepository;

  @Autowired
  public ActivityDefaultGateway(ActivityRepository activityRepository) {
    this.activityRepository = activityRepository;
  }

  @Override
  public Activity save(Activity activity) {
    ActivityEntity savedActivityEntity = activityRepository.save(activity2ActivityEntity(activity));

    return activityEntity2Activity(savedActivityEntity);
  }

  private Activity activityEntity2Activity(ActivityEntity savedActivityEntity) {
    return Activity.builder()
        .name(savedActivityEntity.getName())
        .content(savedActivityEntity.getContent())
        .image(savedActivityEntity.getImage())
        .build();
  }

  private ActivityEntity activity2ActivityEntity(Activity activity) {
    return ActivityEntity.builder()
        .name(activity.getName())
        .content(activity.getContent())
        .image(activity.getImage())
        .build();
  }
}
