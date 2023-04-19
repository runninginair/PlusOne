package com.example.services;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUserId(1L);
        user.setUserName("testuser");
        user.setHeight(170.0);
    }

    @Test
    void getUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);

        assertEquals(user, result);
        verify(userRepository).findById(1L);
    }

    @Test
    void getUserByUsername() {
        when(userRepository.getUserByUserName("testUser")).thenReturn(user);

        User result = userService.getUserByUsername("testUser");

        assertEquals(user, result);
        verify(userRepository).getUserByUserName("testUser");
    }

    @Test
    public void testGetUserById() {
        Long userId = 1L;
        User user = new User(userId, "password", "username", 1);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User result = userService.getUserById(userId);

        assertEquals(user, result);
    }

    @Test
    public void testGetUserByUsername() {
        String username = "username";
        User user = new User(1L, "password", username, 1);
        when(userRepository.getUserByUserName(username)).thenReturn(user);

        User result = userService.getUserByUsername(username);

        assertEquals(user, result);
    }

    @Test
    public void testSaveUser() {
        User user = new User(1L, "password", "username", 1);
        userService.saveUser(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testDeleteUser() {
        String userName = "username";
        when(userRepository.deleteUserByUserName(userName)).thenReturn(1);

        int result = userService.deleteUser(userName);

        assertEquals(1, result);
    }

    @Test
    public void testUpdateHeight() {
        Long userId = 1L;
        Double newHeight = 180.0;
        userService.updateHeight(userId, newHeight);

        verify(userRepository, times(1)).updateUserHeight(userId, newHeight);
    }

    @Test
    public void testUpdateWeight() {
        Long userId = 1L;
        Double newWeight = 70.0;
        userService.updateWeight(userId, newWeight);

        verify(userRepository, times(1)).updateUserWeight(userId, newWeight);
    }

}