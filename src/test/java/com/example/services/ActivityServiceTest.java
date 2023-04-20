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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    @Test
    public void testGetActivityById() {
        // Mock the activity returned by the repository
        Activity activity = new Activity();
        activity.setId(1L);
        when(activityRepository.findById(1L)).thenReturn(Optional.of(activity));

        // Call the method being tested
        Activity result = activityService.getActivityById(1L);

        // Verify the result
        assertEquals(activity, result);
    }

    @Test
    public void testGetAllActivities() {
        // Mock the activities returned by the repository
        List<Activity> activities = new ArrayList<>();
        activities.add(new Activity());
        activities.add(new Activity());
        when(activityRepository.findAll()).thenReturn(activities);

        // Call the method being tested
        List<Activity> result = activityService.getAllActivities();

        // Verify the result
        assertEquals(activities, result);
    }

    @Test
    public void testSaveActivity() {
        // Mock the activity to be saved
        Activity activity = new Activity();
        activity.setId(1L);

        // Call the method being tested
        activityService.saveActivity(activity);

        // Verify that the repository's save method was called with the activity
        verify(activityRepository, times(1)).save(activity);
    }

    @Test
    public void testGetDistanceByTimeRange() {
        // Mock the distance returned by the repository
        double distance = 0.0;
        when(activityRepository.getDistanceByTimeRange(1L, Date.valueOf("2013-02-09"), Date.valueOf("2013-02-19"))).thenReturn(distance);

        // Call the method being tested
        double result = activityService.getDistanceByTimeRange(1L, Date.valueOf("2013-02-09"), Date.valueOf("2013-02-19"));

        // Verify the result
        assertEquals(distance, result);
    }


    public void testGetCaloriesByTimeRange() {
        // Mock the calories returned by the repository
        double calories = 0.0;
        when(activityRepository.getCaloriesByTimeRange(1L, Date.valueOf("2014-02-09"), Date.valueOf("2014-02-19"))).thenReturn(calories);

        // Call the method being tested
        double result = activityService.getDistanceByTimeRange(1L, Date.valueOf("2014-02-09"), Date.valueOf("2014-02-19"));

        // Verify the result
        assertEquals(calories, result);
    }

}
