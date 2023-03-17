package com.example.controller;

import com.example.services.ActivityService;
import com.example.model.Activity;
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

    @GetMapping("/activity/{uid}")
    public Activity plusOne(@PathVariable("uid") long uid){
        System.out.println(uid);
//        Activity activity = activityRepository.findById(uid).get();
        Activity activity = activityService.getActivityById(uid);
        return activity;
    }
}
