package data_analytics_hub.modal;

import data_analytics_hub.dao.PostDAO;

import java.util.ArrayList;

public class Collection {
    protected int collectionId;
    protected ArrayList<String> postIds;
    protected ArrayList<Post> postsList;
    protected PostDAO postDAO = new PostDAO();

    public Collection() {
        //do nothing
    }

    public Collection(int collectionId) {
        this.collectionId = collectionId;
        this.postIds = new ArrayList<>();
        this.postsList = new ArrayList<>();
    }

    public Collection(int collectionId, ArrayList<String> postIds) {
        this.collectionId = collectionId;
        this.postIds = postIds;
        this.postsList = new ArrayList<>();
        for (String postId : postIds) {
            this.postsList.add(postDAO.getPostById(postId));
        }
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public ArrayList<String> getPostIds() {
        return postIds;
    }

    public void setPostIds(ArrayList<String> postIds) {
        this.postIds = postIds;
    }

    public ArrayList<Post> getPostsList() {
        return postsList;
    }

    public void setPostsList(ArrayList<Post> postsList) {
        this.postsList = postsList;
    }

    public String[] toStringArray(){
        String[] collection = new String[2];
        collection[0] = Integer.toString(this.collectionId);
        collection[1] = getPostIdsString();
        return collection;
    }

    private String getPostIdsString(){
        StringBuilder postIdsString = new StringBuilder();
        if (!this.postsList.isEmpty()) {
            for (Post post : this.postsList) {
                postIdsString.append(post.getPostId()).append(":");
            }
            postIdsString.deleteCharAt(postIdsString.length() - 1);
        }
        return postIdsString.toString();
    }
}
