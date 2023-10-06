package data_analytics_hub.functions;

import java.io.File;
import java.util.List;

public interface ICsvExecutor {

    String COMMA_DELIMITER = ",";
    String NEW_LINE_SEPARATOR = "\n";

    String convertToCsv(List<String[]> data);

    List<String[]> convertFromCsv(String csv);

    void writeArrToFile(File file, List<String[]> data);
}
