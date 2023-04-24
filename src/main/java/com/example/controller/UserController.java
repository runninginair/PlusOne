package com.example.controller;

import com.example.model.User;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * The type User controller.
 */
@RestController
public class UserController {

  // For local frontend app
  // public static final String PLUS1_FRONTEND_API = "http://localhost:3000";

  /**
   * For remote frontend app.
   */
  public static final String PLUS1_FRONTEND_API = "https://plusone-frontend.herokuapp.com";

  @Autowired
  UserService userService = new UserService();

  /**
   * Plus one user.
   *
   * @param userId the user id
   * @return the user
   */
  @CrossOrigin(origins = PLUS1_FRONTEND_API)
  @GetMapping("/user/{uid}")
  public User plusOne(@PathVariable("uid") long userId) {
    User user = userService.getUserById(userId);
    return user;
  }

  /**
   * Delete user string.
   *
   * @param userName the username
   * @return the string
   */
  @CrossOrigin(origins = PLUS1_FRONTEND_API)
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

  /**
   * Gets user by username.
   *
   * @param userName the username
   * @return the string
   */
  @CrossOrigin(origins = PLUS1_FRONTEND_API)
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

  /**
   * Update new height by user id string.
   *
   * @param userId    the user id
   * @param newHeight the new height
   * @return the string
   */
  @CrossOrigin(origins = PLUS1_FRONTEND_API)
  @PutMapping("/updateHeightByUserID/{userID}/{newHeight}")
  public String updateNewHeightByUserID(@PathVariable("userID") Long userId,
                                        @PathVariable("newHeight") Double newHeight) {
    User userToUpdate = userService.getUserById(userId);
    if (userToUpdate != null) {
      userService.updateHeight(userId, newHeight);
      return "User height updated successfully";
    } else {
      return "User not found";
    }
  }

  /**
   * Update new weight by user id string.
   *
   * @param userId    the user id
   * @param newWeight the new weight
   * @return the string
   */
  @CrossOrigin(origins = PLUS1_FRONTEND_API)
  @PutMapping("/updateWeightByUserID/{userID}/{newWeight}")
  public String updateNewWeightByUserID(@PathVariable("userID") Long userId,
                                        @PathVariable("newWeight") Double newWeight) {
    User userToUpdate = userService.getUserById(userId);
    if (userToUpdate != null) {
      userService.updateWeight(userId, newWeight);
      return "User weight updated successfully";
    } else {
      return "Sorry, User not found";
    }
  }

}