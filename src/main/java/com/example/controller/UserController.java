package com.example.controller;

import com.example.services.UserService;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  UserService userService = new UserService();


  @GetMapping("/user/{uid}")
  public User plusOne(@PathVariable("uid") long userId){
//    System.out.println(userId);
//        Activity activity = activityRepository.findById(uid).get();
    User user = userService.getUserById(userId);
    return user;
  }

  @GetMapping("/delete/{userName}")
  public String deleteUser(@PathVariable("userName")String userName){
    try{
      int num = userService.deleteUser(userName);
      if (num>-1){
        return "Delete user successfully！";
      }else {
        return "Failed to delete user！";
      }
    }catch (Exception e){
      return "Failed to delete user！";
    }
  }
}
