package com.example.services;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User getUserById(Long userId) {
    return userRepository.findById(userId).get();
  }

  public void saveUser(User user) {
    userRepository.save(user);
  }

  public int deleteUser(String userName){return userRepository.deleteUserByUserName(userName);}

}
