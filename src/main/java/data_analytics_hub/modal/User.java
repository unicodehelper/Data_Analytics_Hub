package data_analytics_hub.modal;

import data_analytics_hub.dao.CollectionDAO;
import data_analytics_hub.dao.PostDAO;

public class User {

    protected int userId;
    protected String username;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected boolean isVip;
    protected Collection collection;
    protected CollectionDAO collectionDAO = new CollectionDAO();

    public User() {
        //do nothing
    }

    // FOR CREATE USER
    public User(int userId, String username, String password, String firstName, String lastName, String email) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.isVip = false;
        this.collection = collectionDAO.getCollectionById(userId);
    }

    public User(int userId, String username, String password, String firstName, String lastName, String email, boolean isVip) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.isVip = isVip;
        this.collection = collectionDAO.getCollectionById(userId);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getIsVip() {
        return isVip;
    }

    public void setIsVip(boolean isVip) {
        this.isVip = isVip;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public void removePost(Post post) {
        collection.removePost(post);
    }

    public void removePost(String postId) {
        collection.removePost(postId);
    }

    public void addPost(Post post) {
        collection.addPost(post);
    }

    public String[] toStringArray() {
        String[] user = new String[7];
        user[0] = String.valueOf(userId);
        user[1] = username;
        user[2] = password;
        user[3] = firstName;
        user[4] = lastName;
        user[5] = email;
        user[6] = String.valueOf(isVip);
        return user;
    }
}
