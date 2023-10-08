package data_analytics_hub;

import data_analytics_hub.functions.CsvExecutor;
import data_analytics_hub.functions.TxtFileExecutor;
import data_analytics_hub.functions.Validator;
import data_analytics_hub.modal.Post;
import data_analytics_hub.modal.User;
import data_analytics_hub.modal.UserFactory;

import java.io.File;
import java.util.ArrayList;

public class Test {

    static TxtFileExecutor txtFileExecutor = new TxtFileExecutor();
    static CsvExecutor csvExecutor = new CsvExecutor();

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

    private static void runReadCsv(){
        String path = "src/main/resources/data_analytics_hub/data/post.csv";
        File file = new File(path);
        ArrayList<Post> content = csvExecutor.convertToPostList(file);
        System.out.println(content);
    }

    public static void main(String[] args) {
//        runEmailTest();
        runReadCsv();
    }
}
