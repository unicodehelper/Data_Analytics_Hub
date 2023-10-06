package data_analytics_hub.functions;

import data_analytics_hub.modal.Post;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CsvExecutor implements ICsvExecutor {

    private static final String[] FILE_HEADER = {"Post ID", "Content", "Author", "Likes", "Shares", "Datetime"};

    public String convertToCsv(List<String[]> data) {
        StringBuilder sb = new StringBuilder();
        for (String[] row : data) {
            for (int i = 0; i < row.length; i++) {
                sb.append(row[i]);
                if (i < row.length - 1) {
                    sb.append(COMMA_DELIMITER);
                }
            }
            sb.append(NEW_LINE_SEPARATOR);
        }
        return sb.toString();
    }

    public List<String[]> convertFromCsv(String csv) {
        List<String[]> data = new ArrayList<>();
        String[] rows = csv.split(NEW_LINE_SEPARATOR);
        for (String row : rows) {
            data.add(row.split(COMMA_DELIMITER));
        }
        return data;
    }

    public void writeArrToFile(File file, List<String[]> data) {
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.write(convertToCsv(data));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportPostToCsv(File file, Post post) {
        List<String[]> data = new ArrayList<>();
        data.add(FILE_HEADER);
        String[] postData = {
                post.getPostId(), post.getContent(),
                post.getAuthor(), String.valueOf(post.getLikes()),
                String.valueOf(post.getShares()), post.getDatetime()
        };
        data.add(postData);
        writeArrToFile(file, data);
    }
}
