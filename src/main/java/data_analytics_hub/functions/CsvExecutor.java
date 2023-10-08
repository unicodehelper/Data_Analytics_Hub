package data_analytics_hub.functions;

import data_analytics_hub.dao.PostDAO;
import data_analytics_hub.modal.Post;
import data_analytics_hub.tools.AlertTools;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CsvExecutor extends FileExecutor implements ICsvExecutor {

    static final PostDAO postDAO = new PostDAO();
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

    public ArrayList<Post> convertToPostList(File file) {
        ArrayList<Post> posts = new ArrayList<>();
        ArrayList<String[]> content = readFileToArray(file.getPath());
        if(content.get(0)[0].matches("ID") || content.get(0)[0].matches("Post ID")) {
            //include header
            content.remove(0);
        } else {
            //not correct format
            return null;
        }
        for (String[] ele : content) {
            if (ele.length != 6 && ele.length != 7) {
                return null;
            }
            posts.add(postDAO.convertArrToPost(ele));
        }
        return posts;
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
