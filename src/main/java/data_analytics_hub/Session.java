package data_analytics_hub;

import data_analytics_hub.dao.CollectionDAO;
import data_analytics_hub.dao.PostDAO;
import data_analytics_hub.dao.UserDAO;
import data_analytics_hub.functions.TxtFileExecutor;
import data_analytics_hub.modal.Collection;
import data_analytics_hub.modal.Post;
import data_analytics_hub.modal.User;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Session {

    public static User currentUser = null;
    public static Post currentPost = null;
    public static Collection currentCollection = null;

    public static ArrayList<User> users = null;
    public static ArrayList<Post> posts = null;
    public static ArrayList<Collection> collections = null;

    //file executor
    public static final TxtFileExecutor txtFileExecutor = new TxtFileExecutor();

    //DAO
    public static final UserDAO userDAO = new UserDAO();
    public static final CollectionDAO collectionDAO = new CollectionDAO();
    public static final PostDAO postDAO = new PostDAO();

    //Dashboard Pane
    public static Pane dashboardPane = null;

    public static void execute() {
        //handle init all data with DAO
        posts = postDAO.initData();
        collections = collectionDAO.initData();
        users = userDAO.initData();
    }

    public static void save() {
        //handle save all data with DAO
        userDAO.saveAll();
        postDAO.saveAll();
        collectionDAO.saveAll();
    }
}
