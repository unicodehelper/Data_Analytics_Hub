package data_analytics_hub.modal;

import java.util.ArrayList;

public class Collection {
    protected String collectionId;
    protected ArrayList<String> postIds;
    protected ArrayList<Post> postsList;

    public Collection() {
        //do nothing
    }

    public Collection(String collectionId, ArrayList<String> postIds) {
        this.collectionId = collectionId;
        this.postIds = postIds;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
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
}
