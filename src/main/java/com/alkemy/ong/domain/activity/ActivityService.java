package com.alkemy.ong.domain.activity;

import com.alkemy.ong.domain.gateway.ActivityGateway;
import com.alkemy.ong.domain.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

  @Autowired
  ActivityGateway activityGateway;

  public Activity save(Activity activity) {
    return activityGateway.save(activity);
  }
}
