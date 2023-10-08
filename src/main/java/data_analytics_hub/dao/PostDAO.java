package data_analytics_hub.dao;

import data_analytics_hub.Session;
import data_analytics_hub.functions.TxtFileExecutor;
import data_analytics_hub.modal.Post;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class PostDAO extends SuperDAO<Post> implements DAO<Post> {

    public ArrayList<Post> initData() {
        ArrayList<Post> posts = new ArrayList<>();
        ArrayList<String[]> fileContent = Session.txtFileExecutor.readFileToArray(TxtFileExecutor.POSTS);
        for(String[] ele : fileContent){
            posts.add(convertArrToPost(ele));
        }
        return posts;
    }

    public void saveAll(){
        ArrayList<String[]> postsContent = new ArrayList<>();
        for (Post post : Session.posts) {
            postsContent.add(post.toStringArray());
        }
        Session.txtFileExecutor.rewriteFile(TxtFileExecutor.POSTS, postsContent);
    }

    public Post convertArrToPost(String[] arr) {
        Post post;
        if (arr.length == 6) {
            post = new Post(arr[0], arr[1], arr[2], Integer.parseInt(arr[3]), Integer.parseInt(arr[4]), arr[5]);
        }
        else {
            int size = arr.length;
            StringBuilder content = new StringBuilder();
            for (int i = 1; i < size - 4; i++) {
                content.append(arr[i]).append(" ");
            }
            content = new StringBuilder(content.toString().replace("\"", ""));
            post = new Post(arr[0], content.toString(), arr[size-4], Integer.parseInt(arr[size-3]), Integer.parseInt(arr[size-2]), arr[size-1]);
        }
        return post;
    }

    public Post getPostById(String id) {
        for (Post post : Session.posts) {
            if (Objects.equals(post.getPostId(), id)) {
//                System.out.println("Found post with id: " + post.getPostId());
                return post;
            }
        }
        return null;
    }

    @Override
    public Optional<Post> get(int id) {
        for (Post post : Session.posts) {
            if (Integer.valueOf(post.getPostId()).equals(id)) {
                return Optional.of(post);
            }
        }
        return Optional.empty();
    }

    public Optional<Post> get(String id) {
        for (Post post : Session.posts) {
            if (post.getPostId().equals(id)) {
                return Optional.of(post);
            }
        }
        return Optional.empty();
    }

    public boolean isPostExist(String[] ids) {
        for (String id : ids) {
            if (getPostById(id) == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ArrayList<Post> getAll() {
        return Session.posts;
    }

    @Override
    public void save(Post post) {
        Session.posts.add(post);
    }

    @Override
    public void update(Post post) {
        //do nothing
    }

    @Override
    public void delete(Post post) {
        Session.posts.remove(post);
    }
}
