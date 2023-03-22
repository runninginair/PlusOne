package com.example.controller;

import com.example.repository.UserRepository;
import com.example.services.UserService;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

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

  @GetMapping("/delete/{userName}")
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

  @GetMapping("/getuserbyusername/{userName}")
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


  @PutMapping("/updateNewHeightbyUserID/{userID}/{newHeight}")
  public String updateNewHeightByUserID(@PathVariable("userID") Long UserID, @PathVariable("newHeight") Double newHeight) {
    User userToUpdate = userService.getUserById(UserID);
    if (userToUpdate != null){
      userToUpdate.setHeight(newHeight);
      return "User height updated successfully";
    }
    else{
      return "User not found";
    }
  }



}