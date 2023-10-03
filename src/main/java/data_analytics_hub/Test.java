package data_analytics_hub;

import data_analytics_hub.functions.TxtFileExecutor;

import java.util.ArrayList;

public class Test {

    private static void runFileTest(){
        TxtFileExecutor txtFileExecutor = new TxtFileExecutor();
        ArrayList<String[]> content = txtFileExecutor.readFileToArray("users");
        System.out.println(content);
    }

    public static void main(String[] args) {
        runFileTest();
    }
}
