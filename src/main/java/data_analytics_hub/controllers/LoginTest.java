package data_analytics_hub.controllers;

import data_analytics_hub.modal.User;
import data_analytics_hub.modal.UserFactory;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    @org.junit.jupiter.api.Test
    public void testUserLogin(){
        User user1 = UserFactory.getUser("tester", "test123");
        User user2 = UserFactory.getUser("user1", "password1");
        assertNotNull(user1);  //user found
        assertNull(user2);  //user not found
    }
}
