package com.example.controller;

import com.example.repository.UserRepository;
import com.example.services.UserService;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

  @Autowired
  UserService userService = new UserService();


  @GetMapping("/user/{uid}")
  public User plusOne(@PathVariable("uid") long userId) {
//    System.out.println(userId);
//        Activity activity = activityRepository.findById(uid).get();
    User user = userService.getUserById(userId);
    return user;
  }

  @DeleteMapping("/deleteUserByUserName/{userName}")
  public String deleteUser(@PathVariable("userName") String userName) {
    try {
      int num = userService.deleteUser(userName);
      if (num > -1) {
        return "Delete user successfully！";
      } else {
        return "Failed to delete user！";
      }
    } catch (Exception e) {
      return "Failed to delete user！";
    }
  }

  @GetMapping("/getUserByUsername/{userName}")
  public String getUserByUsername(@PathVariable("userName") String userName) {
    try {
      User user = userService.getUserByUsername(userName);
      if (user != null) {
        return "Find the user！";
      } else {
        return "Cannot find the user！";
      }
    } catch (Exception e) {
      return "Failed to find the user！";
    }
  }


  @PutMapping("/updateHeightByUserID/{userID}/{newHeight}")
  public String updateNewHeightByUserID(@PathVariable("userID") Long UserID, @PathVariable("newHeight") Double newHeight) {
    User userToUpdate = userService.getUserById(UserID);
    if (userToUpdate != null){
      userService.updateHeight(UserID, newHeight);
      return "User height updated successfully";
    }
    else{
      return "User not found";
    }
  }

  @PutMapping("/updateWeightByUserID/{userID}/{newWeight}")
  public String updateNewWeightByUserID(@PathVariable("userID") Long UserID, @PathVariable("newWeight") Double newWeight) {
    User userToUpdate = userService.getUserById(UserID);
    if (userToUpdate != null){
      userService.updateWeight(UserID, newWeight);
      return "User weight updated successfully";
    }
    else{
      return "Sorry, User not found";
    }
  }


}