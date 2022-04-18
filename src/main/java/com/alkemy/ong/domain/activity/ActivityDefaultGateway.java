package com.alkemy.ong.domain.activity;

import com.alkemy.ong.data.entity.ActivityEntity;
import com.alkemy.ong.data.repository.ActivityRepository;
import com.alkemy.ong.domain.gateway.ActivityGateway;
import com.alkemy.ong.domain.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityDefaultGateway implements ActivityGateway {
  @Autowired
  ActivityRepository activityRepository;

  @Override
  public Activity save(Activity activity) {

    ActivityEntity savedActivityEntity = activityRepository.save(
        ActivityEntity.builder()
            .name(activity.getName())
            .content(activity.getContent())
            .image(activity.getImage())
            .build()
    );

    return Activity.builder()
        .name(savedActivityEntity.getName())
        .content(savedActivityEntity.getContent())
        .image(savedActivityEntity.getImage())
        .build();
  }
}
