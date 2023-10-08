package data_analytics_hub.dao;

import data_analytics_hub.Session;

import static org.junit.jupiter.api.Assertions.*;

public class PostDAOTest {

    @org.junit.jupiter.api.Test
    public void testCheckPostID() {
        PostDAO postDAO = new PostDAO();
        Session.posts = postDAO.initData();
        assertTrue(postDAO.get("1").isPresent());
        assertFalse(postDAO.get("100").isPresent());
    }
}
