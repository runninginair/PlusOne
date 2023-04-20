package com.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getUserId() {
    }

    @Test
    void setUserId() {
    }

    @Test
    void getPassword() {
    }

    @Test
    void setPassword() {
    }

    @Test
    void getUserName() {
    }

    @Test
    void setUserName() {
    }

    @Test
    void getFirstName() {
    }

    @Test
    void setFirstName() {
    }

    @Test
    void getLastName() {
    }

    @Test
    void setLastName() {
    }

    @Test
    void getPhone() {
    }

    @Test
    void setPhone() {
    }

    @Test
    void getUserType() {
    }

    @Test
    void setUserType() {
    }

    @Test
    void getHeight() {
    }

    @Test
    void setHeight() {
    }

    @Test
    void getWeight() {
    }

    @Test
    void setWeight() {
    }

    @Test
    public void testGettersAndSetters() {
        User user = new User();
        user.setUserId(1L);
        user.setPassword("password");
        user.setUserName("username");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPhone("555-555-5555");
        user.setUserType(2);
        user.setHeight(6.0);
        user.setWeight(180.0);

        assertEquals(1L, user.getUserId());
        assertEquals("password", user.getPassword());
        assertEquals("username", user.getUserName());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("555-555-5555", user.getPhone());
        assertEquals(2, user.getUserType().intValue());
        assertEquals(6.0, user.getHeight(), 0.01);
        assertEquals(180.0, user.getWeight(), 0.01);
    }


    @Test
    public void testConstructors() {
        User user1 = new User();
        assertNull(user1.getUserId());
        assertNull(user1.getPassword());
        assertNull(user1.getUserName());
        assertNull(user1.getFirstName());
        assertNull(user1.getLastName());
        assertNull(user1.getPhone());
        assertNull(user1.getUserType());
        assertNull(user1.getHeight());
        assertNull(user1.getWeight());

        User user2 = new User(1L);
        assertEquals(1L, user2.getUserId().longValue());
        assertNull(user2.getPassword());
        assertNull(user2.getUserName());
        assertNull(user2.getFirstName());
        assertNull(user2.getLastName());
        assertNull(user2.getPhone());
        assertNull(user2.getUserType());
        assertNull(user2.getHeight());
        assertNull(user2.getWeight());

        User user3 = new User(1L, "password", "username", 2);
        assertEquals(1L, user3.getUserId().longValue());
        assertEquals("password", user3.getPassword());
        assertEquals("username", user3.getUserName());
        assertNull(user3.getFirstName());
        assertNull(user3.getLastName());
        assertNull(user3.getPhone());
        assertEquals(2, user3.getUserType().intValue());
        assertNull(user3.getHeight());
        assertNull(user3.getWeight());
    }

}