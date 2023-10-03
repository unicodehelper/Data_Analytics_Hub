package data_analytics_hub.modal;

import data_analytics_hub.functions.Encryption;

public class UserFactory {

    private static final Encryption encryption = Encryption.getInstance();

    public UserFactory() {
    }

    public static User createUser(String username, String password, String firstName, String lastName, String email) {
        String newUserId = "1";
        password = encryption.AESEncrypt(password);
        return new User(newUserId, username, password, firstName, lastName, email);
    }

    public static User getUser(String username, String password) {
        password = encryption.AESEncrypt(password);
        return new User();
    }
}
