package com.example.services;

import com.example.model.Activity;
import com.example.model.User;
import com.example.repository.ActivityRepository;
import com.example.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActivityServiceTest {

    @Mock
    private ActivityRepository activityRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ActivityService activityService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getActivitiesByDay() {
        // Arrange
        long uid = 1L;
        String date = "2023-01-01";
        Date sqlDate = Date.valueOf(date);
        User user = new User();
        user.setUserId(uid);
        Activity activity1 = new Activity(user, sqlDate, "Running");
        Activity activity2 = new Activity(user, sqlDate, "Swimming");
        List<Activity> activities = Arrays.asList(activity1, activity2);

        when(userRepository.findById(uid)).thenReturn(java.util.Optional.of(user));
        when(activityRepository.findByUserIdAndDate(user, sqlDate)).thenReturn(activities);

        // Act
        List<Activity> result = activityService.getActivitiesByDay(uid, sqlDate);

        // Assert
        assertEquals(2, result.size());
        assertEquals("Running", result.get(0).getActivityName());
        assertEquals("Swimming", result.get(1).getActivityName());
        verify(userRepository, times(1)).findById(uid);
        verify(activityRepository, times(1)).findByUserIdAndDate(user, sqlDate);
    }

    // Add more test methods for other ActivityService methods
}
