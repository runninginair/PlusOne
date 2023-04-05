package com.example.controller;

import com.example.model.Activity;
import com.example.model.User;
import com.example.services.ActivityService;
import com.example.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ActivityControllerTest {

    @Mock
    private ActivityService activityService;

    @Mock
    private UserService userService;

    @InjectMocks
    private ActivityController activityController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(activityController).build();
    }

    @Test
    void getActivitiesByUserIdAndDate() throws Exception {
        // Arrange
        long uid = 1L;
        String date = "2023-01-01";
        Date sqlDate = Date.valueOf(date);
        User user = new User();
        user.setUserId(uid);
        Activity activity1 = new Activity(user, sqlDate, "Running");
        Activity activity2 = new Activity(user, sqlDate, "Swimming");
        List<Activity> activities = Arrays.asList(activity1, activity2);

        when(userService.getUserById(uid)).thenReturn(user);
        when(activityService.getActivitiesByDay(uid, sqlDate)).thenReturn(activities);

        // Act & Assert
        mockMvc.perform(get("/activities/{uid}/date/{date}", uid, date)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].activityName").value("Running"))
                .andExpect(jsonPath("$[1].activityName").value("Swimming"));

        verify(userService, times(1)).getUserById(uid);
        verify(activityService, times(1)).getActivitiesByDay(uid, sqlDate);
    }

    // Add more test methods for other ActivityController methods
}
