package data_analytics_hub.dao;

import data_analytics_hub.Session;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {

    @org.junit.jupiter.api.Test
    public void testCheckUsername() {
        UserDAO userDAO = new UserDAO();
        Session.users = userDAO.initData();
        assertTrue(userDAO.isUsernameExist("tester"));
        assertFalse(userDAO.isUsernameExist("username"));
    }
}
