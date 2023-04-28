package com.example.controller;

import com.example.model.Activity;
import com.example.model.User;
import com.example.services.ActivityService;
import com.example.services.UserService;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Activity controller.
 */
@RestController
public class ActivityController {

  // For local frontend app use following API
   public static final String PLUS1_FRONTEND_API = "http://localhost:3000";

  /**
   * For remote (Heroku) frontend app.
   */
//  public static final String PLUS1_FRONTEND_API = "https://plusone-frontend.herokuapp.com";

  @Autowired
  ActivityService activityService = new ActivityService();

  @Autowired
  UserService userService = new UserService();

  /**
   * Gets activities by user id and date.
   *
   * @param uid  the user id
   * @param date the date
   * @return the activities by user id and date
   */
  @CrossOrigin(origins = PLUS1_FRONTEND_API)
  @GetMapping("/activities/{uid}/date/{date}")
  public List<Activity> getActivitiesByUserIdAndDate(
          @PathVariable("uid") long uid, @PathVariable("date") String date) {
    System.out.println("uid = " + uid + ", date = " + date);
    return activityService.getActivitiesByDay(uid, Date.valueOf(date));
  }

  /**
   * create a new activity by userid, date, activity name.
   *
   * @param activity the activity
   * @return the response entity
   */
  @CrossOrigin(origins = PLUS1_FRONTEND_API)
  @PostMapping(path = "/activities", consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) {
    User user = userService.getUserById(activity.getUserId().getUserId());
    if (user == null) {
      return new ResponseEntity<>(activity, HttpStatus.BAD_REQUEST);
    }
    activityService.saveActivity(activity);
    return new ResponseEntity<>(activity, HttpStatus.CREATED);
  }

  /**
   * Gets distance by time range.
   *
   * @param userId the user id
   * @param start  the start date
   * @param end    the end date
   * @return the distance by time range
   */
  @CrossOrigin(origins = PLUS1_FRONTEND_API)
//  @GetMapping("/activity/getDistanceByTimeRange/{userId}/{start}/{end}")
  @GetMapping("/activities/user/{userId}/distance/start/{start}/end/{end}")
  public double getDistanceByTimeRange(@PathVariable("userId") Long userId,
                                       @PathVariable("start") Date start,
                                       @PathVariable("end") Date end) {
    return activityService.getDistanceByTimeRange(userId, start, end);
  }

  /**
   * Gets calories by time range.
   *
   * @param userId the user id
   * @param start  the start date
   * @param end    the end date
   * @return the calories by time range
   */
  @CrossOrigin(origins = PLUS1_FRONTEND_API)
//  @GetMapping("/activity/getCaloriesByTimeRange/{userId}/{start}/{end}")
  @GetMapping("/activities/user/{userId}/calories/start/{start}/end/{end}")
  public double getCaloriesByTimeRange(@PathVariable("userId") Long userId,
                                       @PathVariable("start") Date start,
                                       @PathVariable("end") Date end) {
    return activityService.getCaloriesByTimeRange(userId, start, end);
  }

}