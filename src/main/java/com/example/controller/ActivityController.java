package com.example.controller;

import com.example.model.User;
import com.example.services.ActivityService;
import com.example.model.Activity;
import com.example.services.UserService;
import java.sql.Date;
import java.util.List;
import javax.accessibility.AccessibleIcon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivityController {
  
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
  public List<Activity> getActivitiesByUserIdAndDate(@PathVariable("uid") long uid,
      @PathVariable("date") String date) {
    System.out.println("uid = " + uid + ", date = " + date);
    List<Activity> activities = activityService.getActivitiesByDay(uid, Date.valueOf(date));
    return activities;
  }

  @PostMapping(path = "/activities", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {
      MediaType.APPLICATION_JSON_VALUE,
      MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Activity> create(@RequestBody Activity activity) {
    User user = userService.getUserById(activity.getUserId().getUserId());
    System.out.println("user:" + user + " date =" + activity.getDate() + " name:" + activity.getActivityName());
    if (user == null) {
      return new ResponseEntity<>(activity, HttpStatus.BAD_REQUEST);
    }
    activityService.saveActivity(activity);
    return new ResponseEntity<>(activity, HttpStatus.CREATED);
  }

}
