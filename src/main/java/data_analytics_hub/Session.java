package data_analytics_hub;

import data_analytics_hub.modal.Collection;
import data_analytics_hub.modal.Post;
import data_analytics_hub.modal.User;

import java.util.ArrayList;

public class Session {

    public static User currentUser = null;
    public static ArrayList<User> users = null;
    public static ArrayList<Post> posts = null;
    public static ArrayList<Collection> collections = null;

    public static void execute() {
        //do nothing
    }
}
