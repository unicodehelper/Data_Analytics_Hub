package data_analytics_hub.functions;

import java.util.ArrayList;

public interface ITxtFileExecutor {

    boolean rewriteFile(String filename, ArrayList<String[]> data);

    void writeFile(String filename, String content);

    void writeArrToFile(String filename, String[] data);

    boolean writeArrToFile(String filename, ArrayList<String[]> data);
}
