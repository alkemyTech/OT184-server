package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.ActivityEntity;
import com.alkemy.ong.data.members.repository.ActivityRepository;
import com.alkemy.ong.domain.activity.Activity;
import com.alkemy.ong.domain.activity.ActivityGateway;
import org.springframework.stereotype.Component;

@Component
public class ActivityDefaultGateway implements ActivityGateway {
  ActivityRepository activityRepository;

  public ActivityDefaultGateway(ActivityRepository activityRepository) {
    this.activityRepository = activityRepository;
  }

  @Override
  public Activity save(Activity activity) {
    ActivityEntity savedActivityEntity = activityRepository.save(toEntity(activity));

    return toModel(savedActivityEntity);
  }

  private Activity toModel(ActivityEntity savedActivityEntity) {
    return Activity.builder()
        .id(savedActivityEntity.getId())
        .name(savedActivityEntity.getName())
        .content(savedActivityEntity.getContent())
        .image(savedActivityEntity.getImage())
        .build();
  }

  private ActivityEntity toEntity(Activity activity) {
    return ActivityEntity.builder()
        .id(activity.getId())
        .name(activity.getName())
        .content(activity.getContent())
        .image(activity.getImage())
        .build();
  }
}
