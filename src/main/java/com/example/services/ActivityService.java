package com.example.services;

import com.example.model.Activity;
import com.example.model.User;
import com.example.repository.ActivityRepository;
import com.example.repository.UserRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserRepository userRepository;

    public Activity getActivityById(long uid) {
        return activityRepository.findById(uid).get();
    }
    public List<Activity> getActivitiesByDay(long uid, Date date) {
        User user = userRepository.findById(uid).orElse(null); // Assuming you have a UserRepository
        List<Activity> activities = new ArrayList<>();
        if (user != null) {
            activities = activityRepository.findByUserIdAndDate(user, date);
        }
        return activities;
    }

    public List<Activity> getAllActivities() {
        return (List<Activity>) activityRepository.findAll();
    }

    public void saveActivity(Activity activity) {
        activityRepository.save(activity);
    }


    public double getDistanceByTimeRange(Long userId, Date start, Date end){
        return activityRepository.getDistanceByTimeRange(userId,start,end);
    }

}
