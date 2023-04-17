package com.example.controller;

import com.example.model.Activity;
import com.example.model.User;
import com.example.services.ActivityService;
import com.example.services.UserService;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
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
    public void testGetActivitiesByUserIdAndDate() {
        // Set up test data
        long userId = 1l;
        User user = new User(userId);
        Date date = Date.valueOf("2013-02-09");
        List<Activity> activities = Arrays.asList(
            new Activity(user, date, "running"),
            new Activity(user, date, "walking"),
            new Activity(user, date, "transport")
        );
        Mockito.when(activityService.getActivitiesByDay(userId, date)).thenReturn(activities);

        // Call the method to be tested
        List<Activity> result = activityController.getActivitiesByUserIdAndDate(userId, date.toString());

        // Verify the result
        assertEquals(activities, result);
        Mockito.verify(activityService).getActivitiesByDay(userId, date);
    }


//    @Test
//    public void createActivityWithExistingUser() {
//        User user = new User(1l);
//        Activity activity = new Activity(user, Date.valueOf("2013-02-09"), "running");
//
//        ActivityService activityService = mock(ActivityService.class);
//        doNothing().when(activityService).saveActivity(activity);
//
//        Activity createdActivity = activityController.create(activity).getBody();
//
//        assertEquals(activity, createdActivity);
//    }

//    @Test
//    public void createActivityWithNonExistingUser() {
//        User user = new User();
//        user.setUserId(1L);
//
//        // create an activity object
//        Activity activity = new Activity();
//        activity.setUserId(user);
//        activity.setDate(new Date(new GregorianCalendar(2023, Calendar.MARCH, 22).getTimeInMillis()));
//        activity.setActivityName("Test activity");
//
//        // mock the getUserById method of the UserService to return null
//        when(userService.getUserById(user.getUserId())).thenReturn(null);
//
//        // call the create method of the ActivityController
//        ResponseEntity<Activity> responseEntity = activityController.create(activity);
//
//        // assert that the status code of the response is HttpStatus.BAD_REQUEST
//        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//
//        // assert that the activity object returned by the create method is the same as the activity object passed as parameter
//        assertEquals(activity, responseEntity.getBody());
//    }

    @Test
    public void getDistanceByTimeRange() {
        Long userId = 1l;
        Date start = Date.valueOf("2013-02-10");
        Date end = Date.valueOf("2013-02-11");

        double mockTotalDistance = 101733.0;
        Mockito.when(activityService.getDistanceByTimeRange(userId, start,end)).thenReturn(mockTotalDistance);

        // Call the method to be tested
        double total = activityController.getDistanceByTimeRange(userId, start, end);

        // Verify the result
        assertEquals(mockTotalDistance, total, 2);
        Mockito.verify(activityService).getDistanceByTimeRange(userId, start,end);

    }

//@ExtendWith(MockitoExtension.class)
//class ActivityControllerTest {
//
//    @Mock
//    private ActivityService activityService;
//
//    @Mock
//    private UserService userService;
//
//    @InjectMocks
//    private ActivityController activityController;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(activityController).build();
//    }
//
//    @Test
//    void getActivitiesByUserIdAndDate() throws Exception {
//        // Arrange
//        long uid = 1L;
//        String date = "2023-01-01";
//        Date sqlDate = Date.valueOf(date);
//        User user = new User();
//        user.setUserId(uid);
//        Activity activity1 = new Activity(user, sqlDate, "Running");
//        Activity activity2 = new Activity(user, sqlDate, "Swimming");
//        List<Activity> activities = Arrays.asList(activity1, activity2);
//
//        when(userService.getUserById(uid)).thenReturn(user);
//        when(activityService.getActivitiesByDay(uid, sqlDate)).thenReturn(activities);
//
//        // Act & Assert
//        mockMvc.perform(get("/activities/{uid}/date/{date}", uid, date)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].activityName").value("Running"))
//                .andExpect(jsonPath("$[1].activityName").value("Swimming"));
//
//        verify(userService, times(1)).getUserById(uid);
//        verify(activityService, times(1)).getActivitiesByDay(uid, sqlDate);
//    }

    // Add more test methods for other ActivityController methods
}
