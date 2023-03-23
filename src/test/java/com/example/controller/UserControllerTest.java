package com.example.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import com.example.model.User;
import com.example.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
class UserControllerTest {

  @Mock
  private UserService userService;

  @InjectMocks
  private UserController userController;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
  }

  @Test
  void plusOne() {
    Long userId = 1l;
    User mockUser = new User(userId);

    Mockito.when(userService.getUserById(userId)).thenReturn(mockUser);

    User user = userController.plusOne(userId);
    assertEquals(mockUser, user);
    Mockito.verify(userService).getUserById(userId);
  }

  @Test
  void deleteUser() {
    
  }
}