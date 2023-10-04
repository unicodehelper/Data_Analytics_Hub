package data_analytics_hub;

import data_analytics_hub.functions.TxtFileExecutor;
import data_analytics_hub.functions.Validator;
import data_analytics_hub.modal.User;
import data_analytics_hub.modal.UserFactory;

import java.util.ArrayList;

public class Test {

    static TxtFileExecutor txtFileExecutor = new TxtFileExecutor();

    private static void runFileTest(){
        ArrayList<String[]> content = txtFileExecutor.readFileToArray("users");
        System.out.println(content);
    }

    private static void runUserTest(){
        Session.execute();
        User user = UserFactory.createUser("tester", "test123", "test", "test", "test@gmail.com");
        txtFileExecutor.writeArrToFile("users", user.toStringArray());
    }

    private static void runEmailTest(){
        String email = "testing123@gmail.com";
        boolean isEmailValid = Validator.validateEmail(email);
        System.out.println(isEmailValid);
    }

    public static void main(String[] args) {
        runEmailTest();
    }
}
