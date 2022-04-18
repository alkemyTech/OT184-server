package com.alkemy.ong.domain.activity;

import org.springframework.stereotype.Service;

@Service
public class ActivityService {

  private final ActivityGateway activityGateway;

  public ActivityService(ActivityGateway activityGateway) {
    this.activityGateway = activityGateway;
  }

  public Activity save(Activity activity) {
    return activityGateway.save(activity);
  }
}
