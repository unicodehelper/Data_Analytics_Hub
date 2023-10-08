package data_analytics_hub.functions;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class FileExecutor {
    final String filePath = "src\\main\\resources\\data_analytics_hub\\data\\";

    public ArrayList<String> readFile(String path){
        ArrayList<String> content = new ArrayList<>();
        try{
            File file = new java.io.File(path);
            Scanner scanner = new java.util.Scanner(file);

            while(scanner.hasNextLine()){
                String data = scanner.nextLine();
                content.add(data);
            }
            scanner.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return content;
    }

    public ArrayList<String[]> readFileToArray(String path){
        ArrayList<String[]> content = new ArrayList<>();
        ArrayList<String> fileContent = readFile(path);
        for(String ele : fileContent){
            content.add(ele.split(","));
        }
        return content;
    }
}
