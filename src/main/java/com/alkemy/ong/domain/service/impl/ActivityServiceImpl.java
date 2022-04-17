package com.alkemy.ong.domain.service.impl;

import com.alkemy.ong.domain.gateway.ActivityGateway;
import com.alkemy.ong.domain.model.Activity;
import com.alkemy.ong.domain.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService {

  @Autowired
  ActivityGateway activityGateway;

  @Override
  public Activity save(Activity activity) {
    return activityGateway.save(activity);
  }
}
