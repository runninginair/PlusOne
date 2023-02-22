package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    public Activity getActivityById(long uid) {
        return activityRepository.findById(uid).get();
    }

    public List<Activity> getAllActivities() {
        return (List<Activity>) activityRepository.findAll();
    }

    public void saveActivity(Activity activity) {
        activityRepository.save(activity);
    }
}
