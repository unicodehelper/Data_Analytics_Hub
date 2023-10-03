package data_analytics_hub.modal;

import data_analytics_hub.Session;
import data_analytics_hub.functions.Encryption;

public class UserFactory {

    private static final Encryption encryption = Encryption.getInstance();

    public UserFactory() {
    }

    public static User createUser(String username, String password, String firstName, String lastName, String email) {
        String newUserId = Session.users.get(Session.users.size() - 1).getUserId() + 1;
        password = encryption.AESEncrypt(password);
        return new User(newUserId, username, password, firstName, lastName, email);
    }

    public static User getUser(String username, String password) {
        password = encryption.AESEncrypt(password);
        for (User user : Session.users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
