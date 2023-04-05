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
        when(userRepository.getUserByUserName("testuser")).thenReturn(user);

        User result = userService.getUserByUsername("testuser");

        assertEquals(user, result);
        verify(userRepository).getUserByUserName("testuser");
    }

    // You can continue writing tests for other methods in the same way.
}
