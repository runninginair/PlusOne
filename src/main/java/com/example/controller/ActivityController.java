package com.example.controller;

import com.example.model.User;
import com.example.services.ActivityService;
import com.example.model.Activity;
import com.example.services.UserService;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ActivityController {

    /** For local frontend app */
    // public static final String PLUS1_FRONTEND_API = "http://localhost:3000";

    /**
     * For remote (Heroku) frontend app
     */
    public static final String PLUS1_FRONTEND_API = "https://plusone-frontend.herokuapp.com";

    @Autowired
    ActivityService activityService = new ActivityService();

    @Autowired
    UserService userService = new UserService();


    @CrossOrigin(origins = PLUS1_FRONTEND_API)
    @GetMapping("/activities/{uid}/date/{date}")
    public List<Activity> getActivitiesByUserIdAndDate(@PathVariable("uid") long uid, @PathVariable("date") String date) {
        System.out.println("uid = " + uid + ", date = " + date);
        List<Activity> activities = activityService.getActivitiesByDay(uid, Date.valueOf(date));
        return activities;
    }

    /**
     * create a new activity by userid, date, activity name
     */
    @CrossOrigin(origins = PLUS1_FRONTEND_API)
    @PostMapping(path = "/activities", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) {
        User user = userService.getUserById(activity.getUserId().getUserId());
//        System.out.println("user:" + user + " date =" + activity.getDate() + " name:" + activity.getActivityName());
        if (user == null) {
            return new ResponseEntity<>(activity, HttpStatus.BAD_REQUEST);
        }
        activityService.saveActivity(activity);
        return new ResponseEntity<>(activity, HttpStatus.CREATED);

//    @PostMapping("/activity/create/{userid}/date/{date}/name/{name}")
//    public boolean createActivity(@PathVariable("userid") long uid, @PathVariable("date") String dateString, @PathVariable("name") String activityName) {
        //        System.out.println("uid = " + uid + ", date = " + date);
//        User user = userService.getUserById(uid);
//        if (user == null) {
//            return false;
//        }
//        try {
//            Date date = Date.valueOf(dateString);
//            Activity activity = new Activity(user, date, activityName);
//            activityService.saveActivity(activity);
//        } catch (IllegalArgumentException e) {
//            System.out.println("Invalid date format: " + dateString);
//            return false;
//        }
//        return true;
    }

    @CrossOrigin(origins = PLUS1_FRONTEND_API)
    @GetMapping("/activity/getDistanceByTimeRange/{userId}/{start}/{end}")
    public double getDistanceByTimeRange(@PathVariable("userId") Long userId, @PathVariable("start") Date start, @PathVariable("end") Date end) {
        return activityService.getDistanceByTimeRange(userId, start, end);
    }

    @CrossOrigin(origins = PLUS1_FRONTEND_API)
    @GetMapping("/activity/getCaloriesByTimeRange/{userId}/{start}/{end}")
    public double getCaloriesByTimeRange(@PathVariable("userId") Long userId, @PathVariable("start") Date start, @PathVariable("end") Date end) {
        return activityService.getCaloriesByTimeRange(userId, start, end);
    }

}