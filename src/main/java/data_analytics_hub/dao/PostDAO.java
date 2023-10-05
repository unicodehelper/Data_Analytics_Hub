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
            posts.add(
                    new Post(ele[0], ele[1], ele[2], Integer.parseInt(ele[3]), Integer.parseInt(ele[4]), ele[5])
            );
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

    public Post getPostById(String id) {
        for (Post post : Session.posts) {
            if (Objects.equals(post.getPostId(), id)) {
//                System.out.println("Found post with id: " + post.getPostId());
                return post;
            }
        }
        return new Post();
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

    @Override
    public ArrayList<Post> getAll() {
        return Session.posts;
    }

    @Override
    public void save(Post post) {
        Session.posts.add(post);
    }

    @Override
    public void update(Post post, String[] params) {
        //do nothing
    }

    @Override
    public void delete(Post post) {
        Session.posts.remove(post);
    }
}
