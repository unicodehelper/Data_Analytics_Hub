package data_analytics_hub.functions;

import java.io.File;
import java.util.List;

public interface ICsvExecutor {

    String COMMA_DELIMITER = ",";
    String NEW_LINE_SEPARATOR = "\n";

    String convertToCsv(List<String[]> data);

    void writeArrToFile(File file, List<String[]> data);
}
