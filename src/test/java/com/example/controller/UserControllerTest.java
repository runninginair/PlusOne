package com.example.controller;

import com.example.model.User;
import com.example.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUserId(1L);
        user.setUserName("testuser");
        user.setHeight(170.0);
        user.setWeight(88.88);
    }

    @Test
    void getUserById() {
        when(userService.getUserById(1L)).thenReturn(user);

        User result = userController.plusOne(1L);

        assertEquals(user, result);
        verify(userService).getUserById(1L);
    }

    @Test
    void deleteUser() {
        when(userService.deleteUser("testuser")).thenReturn(1);

        String result = userController.deleteUser("testuser");

        assertEquals("Delete user successfully！", result);
        verify(userService).deleteUser("testuser");
    }

    @Test
    void getUserByUsername() {
        when(userService.getUserByUsername("testuser")).thenReturn(user);

        String result = userController.getUserByUsername("testuser");

        assertEquals("Find the user！", result);
        verify(userService).getUserByUsername("testuser");
    }

    @Test
    void updateNewHeightByUserID() {
        when(userService.getUserById(1L)).thenReturn(user);

        String result = userController.updateNewHeightByUserID(1L, 180.0);

        assertEquals("User height updated successfully", result);
        verify(userService).getUserById(1L);
    }

    @Test
    void updateNewWeightByUserID() {
        when(userService.getUserById(1L)).thenReturn(user);

        String result = userController.updateNewWeightByUserID(1L, 88.8);

        assertEquals("User weight updated successfully", result);
        verify(userService).getUserById(1L);
    }
}