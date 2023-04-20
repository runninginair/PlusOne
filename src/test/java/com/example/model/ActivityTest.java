package com.example.model;

import com.example.services.ActivityService;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ActivityTest {

    @Test
    void getUserId() {
    }

    @Test
    void setUserId() {
    }

    @Test
    void getId() {
    }

    @Test
    void setId() {
    }

    @Test
    void getDate() {
    }

    @Test
    void setDate() {
    }

    @Test
    void getGroupName() {
    }

    @Test
    void setGroupName() {
    }

    @Test
    void getActivityName() {
    }

    @Test
    void setActivityName() {
    }

    @Test
    void getSteps() {
    }

    @Test
    void setSteps() {
    }

    @Test
    void getCalories() {
    }

    @Test
    void setCalories() {
    }

    @Test
    void getDistance() {
    }

    @Test
    void setDistance() {
    }

    @Test
    void getDuration() {
    }

    @Test
    void setDuration() {
    }

    @Test
    public void testActivityCreation() {
        User user = new User(7L);
        Date date = Date.valueOf("2013-02-09");
        String activityName = "Walking";

        Activity activity = new Activity(user, Date.valueOf("2013-02-09"), activityName);


        activity.setGroupName("Test Group");
        activity.setSteps(1000);
        activity.setCalories(200);
        activity.setDistance(5.5);
        activity.setDuration(30.0);

        assertEquals("Test Group", activity.getGroupName());
        assertEquals(Integer.valueOf(1000), activity.getSteps());
        assertEquals(Integer.valueOf(200), activity.getCalories());
        assertEquals(Double.valueOf(5.5), activity.getDistance());
        assertEquals(Double.valueOf(30.0), activity.getDuration());

        activity.setGroupName("Test Group");
        activity.setSteps(1000);
        activity.setCalories(200);
        activity.setDistance(5.5);
        activity.setDuration(30.0);

        assertNotNull(activity);
        assertEquals(user, activity.getUserId());
        assertEquals(date, activity.getDate());
        assertEquals(activityName, activity.getActivityName());
    }

}