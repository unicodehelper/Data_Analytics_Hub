package data_analytics_hub.functions;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TxtFileExecutor implements IFileExecutor{
    private static final String filePath = "src\\main\\resources\\data_analytics_hub\\data\\";
    public static final String USERS = "users";
    public static final String POSTS = "posts";
    public static final String COLLECTIONS = "collections";

    @Override
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

    @Override
    public ArrayList<String[]> readFileToArray(String filename){
        String path = filePath + filename + ".txt";
        ArrayList<String[]> content = new ArrayList<>();
        ArrayList<String> fileContent = readFile(path);
        for(String ele : fileContent){
            content.add(ele.split("\\|"));
        }
        return content;
    }

    @Override
    public boolean rewriteFile(String filename, ArrayList<String[]> data){
        File file = new File(filePath + filename + ".txt");
        file.delete();
        return writeArrToFile(filename, data);
    }

    @Override
    public void writeFile(String filename, String content){
        try{
            String path = filePath + filename + ".txt";
            File file = new File(path);
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(path, true);
            fw.write(content + "\n");
            fw.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void writeArrToFile(String filename, String[] data){
        String content = String.join("|", data);
        writeFile(filename, content);
    }

    @Override
    public boolean writeArrToFile(String filename, ArrayList<String[]> data){
        for(String[] ele : data){
            writeArrToFile(filename, ele);
        }
        return true;
    }

}
