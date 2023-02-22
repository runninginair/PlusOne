package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivityController {

    @Autowired
    ActivityRepository activityRepository;

    @GetMapping("/activity/{uid}")
    public Activity plusOne(@PathVariable("uid") long uid){
        System.out.println(uid);
        Activity activity = activityRepository.findById(uid).get();
        return activity;
    }
}
