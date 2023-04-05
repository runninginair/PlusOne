package com.example.controller;

import com.example.model.User;
import com.example.services.ActivityService;
import com.example.model.Activity;
import com.example.services.UserService;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivityController {

//    @Autocwired
////    ActivityRepository ativityRepository;

    @Autowired
    ActivityService activityService = new ActivityService();

    @Autowired
    UserService userService = new UserService();

//    @GetMapping("/activity/{uid}")
//    public Activity plusOne(@PathVariable("uid") long uid){
//        System.out.println(uid);
////        Activity activity = activityRepository.findById(uid).get();
//        Activity activity = activityService.getActivityById(uid);
//        return activity;
//    }

    @GetMapping("/activities/{uid}/date/{date}")
    public List<Activity> getActivitiesByUserIdAndDate(@PathVariable("uid") long uid, @PathVariable("date") String date){
        System.out.println("uid = " + uid + ", date = " + date);
        List<Activity> activities = activityService.getActivitiesByDay(uid, Date.valueOf(date));
        return activities;
    }

    /**
     * create a new activity by userid, date, activity name
     * @param uid
     * @param dateString
     * @param activityName
     */
    @GetMapping("/activity/create/{userid}/date/{date}/name/{name}")
    public boolean createActivity(@PathVariable("userid") long uid, @PathVariable("date") String dateString, @PathVariable("name") String activityName){
//        System.out.println("uid = " + uid + ", date = " + date);
        User user = userService.getUserById(uid);
        if (user == null) {
            return false;
        }
        try {
            Date date = Date.valueOf(dateString);
            Activity activity = new Activity(user, date, activityName);
            activityService.saveActivity(activity);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format: " + dateString);
            return false;
        }
        return true;
    }


    @GetMapping("/activity/getDistanceByTimeRange/{userId}/{start}/{end}")
    public double getDistanceByTimeRange(@PathVariable("userId")Long userId, @PathVariable("start")Date start, @PathVariable("end")Date end){
        return activityService.getDistanceByTimeRange(userId,start,end);
    }

    @GetMapping("/activity/getCaloriesyTimeRange/{userId}/{start}/{end}")
    public double getCaloriesByTimeRange(@PathVariable("userId")Long userId, @PathVariable("start")Date start, @PathVariable("end")Date end){
        return activityService.getCaloriesByTimeRange(userId,start,end);
    }


}
