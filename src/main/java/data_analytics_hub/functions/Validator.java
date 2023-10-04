package data_analytics_hub.functions;

public class Validator {

    private static final String USERNAME_REGEX = "^[a-zA-Z0-9]{5,18}$";
    private static final String NAME_REGEX = "^[a-zA-Z]{2,10}$";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

    public static boolean validateUsername(String username){
        return username.matches(USERNAME_REGEX);
    }

    public static boolean validateName(String name){
        return name.matches(NAME_REGEX);
    }

    public static boolean validateEmail(String email){
        return email.matches(EMAIL_REGEX);
    }

    public static boolean validatePassword(String password){
        return password.length() >= 8;
    }

}
