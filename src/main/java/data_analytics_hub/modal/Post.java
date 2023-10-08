package data_analytics_hub.modal;

public class Post {
    protected String postId;
    protected String content;
    protected String author;
    protected int likes;
    protected int shares;
    protected String datetime;

    public Post() {
        //do nothing
    }

    public Post(String[] data) {
        this.postId = data[0];
        this.content = data[1];
        this.author = data[2];
        this.likes = Integer.parseInt(data[3]);
        this.shares = Integer.parseInt(data[4]);
        this.datetime = data[5];
    }

    public Post(String postId, String content, String author, int likes, int shares, String datetime) {
        this.postId = postId;
        this.content = content;
        this.author = author;
        this.likes = likes;
        this.shares = shares;
        this.datetime = datetime;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String[] toStringArray(){
        String[] post = new String[6];
        post[0] = this.postId;

        if(content.contains(",")){
            post[1] = "\"" + this.content + "\"";
        }else post[1] = this.content;

        post[2] = this.author;
        post[3] = Integer.toString(this.likes);
        post[4] = Integer.toString(this.shares);
        post[5] = this.datetime;
        return post;
    }
}
