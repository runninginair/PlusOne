package com.example.services;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type User service.
 */
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  /**
   * Gets user by id.
   *
   * @param userId the user id
   * @return the user by id
   */
  public User getUserById(Long userId) {
    return userRepository.findById(userId).get();
  }

  /**
   * get User by username.
   *
   * @param username the username
   * @return the user by username
   */
  public User getUserByUsername(String username) {
    return userRepository.getUserByUserName(username);
  }

  /**
   * Save user.
   *
   * @param user the user
   */
  public void saveUser(User user) {
    userRepository.save(user);
  }

  /**
   * Delete user int.
   *
   * @param userName the user name
   * @return the int
   */
  public int deleteUser(String userName) {
    return userRepository.deleteUserByUserName(userName);
  }

  /**
   * update new Height by user ID.
   *
   * @param userId    the user id
   * @param newHeight the new height
   */
  public void updateHeight(Long userId, Double newHeight) {
    userRepository.updateUserHeight(userId, newHeight);
  }

  /**
   * Update weight.
   *
   * @param userId    the user id
   * @param newWeight the new weight
   */
  public void updateWeight(Long userId, Double newWeight) {
    userRepository.updateUserWeight(userId, newWeight);
  }

}
