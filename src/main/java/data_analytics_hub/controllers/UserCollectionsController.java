package data_analytics_hub.controllers;


import data_analytics_hub.Application;
import data_analytics_hub.Session;
import data_analytics_hub.modal.Post;
import data_analytics_hub.tools.AlertTools;
import data_analytics_hub.tools.FxTools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class UserCollectionsController {

    @FXML
    private TableView<Post> tableCollection;

    @FXML
    private TableColumn<Post, String> colPostID, colDatetime, colContent;

    @FXML
    private TableColumn<Post, Integer> colLikes;

    @FXML
    private TextField txtSearch;

    @FXML
    private Button btnViewPost;

    @FXML
    private Label lblCurrentPost;

    @FXML
    private ComboBox<String> cmbMethod;

    private final Map<String, String> searchMethods = Map.of(
            "PostID", "Search by PostID",
            "Likes", "Top N Posts by Likes"
    );

    private ObservableList<Post> postList;

    @FXML
    void initialize(){
        initTable();
        initComboBox();
        initButtonDisable();
        initDataRowListener();
        initSearchFieldListener();
    }

    private void initTable(){
        ArrayList<Post> posts = Session.currentUser.getCollection().getPostsList();
        ObservableList<Post> data = FXCollections.observableArrayList();
        for (Post post : posts) {
            if (post != null) {
                data.add(post);
            }
        }

        colPostID.setCellValueFactory(new PropertyValueFactory<>("PostId"));
        colContent.setCellValueFactory(new PropertyValueFactory<>("Content"));
        colLikes.setCellValueFactory(new PropertyValueFactory<>("Likes"));
        colDatetime.setCellValueFactory(new PropertyValueFactory<>("Datetime"));

        postList = data;
        tableCollection.setItems(data);
    }

    private void initComboBox(){
        //add map value
        cmbMethod.getItems().addAll(searchMethods.values());
        //set default value
        cmbMethod.setValue(searchMethods.get("PostID"));
    }

    private void initButtonDisable(){
        btnViewPost.setDisable(true);
    }

    private void initDataRowListener(){
        tableCollection.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Session.currentPost = newSelection;
                lblCurrentPost.setText(newSelection.getPostId());
                btnViewPost.setDisable(false);
            }
        });
    }

    private void initSearchFieldListener(){
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) tableCollection.setItems(postList);
            else searched();
        });
        cmbMethod.valueProperty().addListener((observable, oldValue, newValue) -> txtSearch.setText(""));
    }

    @FXML
    void btnSearchClicked() {
        if (!isFieldValid()) return;

        searched();
    }

    private void searched(){
        String searchType = searchMethods.entrySet().stream()
                .filter(entry -> Objects.equals(entry.getValue(), cmbMethod.getValue()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        String searchValue = txtSearch.getText();
        handleSearch(searchType, searchValue);
    }

    private void handleSearch(String searchType, String searchValue){
        ObservableList<Post> data = postList;
        if (Objects.equals(searchType, "PostID")){
            //filter table by postID
            data = data.filtered(post -> Objects.equals(post.getPostId(), searchValue));
        }
        else if (Objects.equals(searchType, "Likes")){
            //filter table and sort by likes and top N
            data = data.sorted((post1, post2) -> post2.getLikes() - post1.getLikes());
            int topN = Integer.parseInt(searchValue);
            ObservableList<Post> finalData = data;
            data = data.filtered(post -> finalData.indexOf(post) < topN);
        }
        tableCollection.setItems(data);
    }

    @FXML
    void btnViewPostClicked() {
        try {
            FxTools.initFXML(Session.dashboardPane, "view-post");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnAddPostClicked() {
        Application.changeScene("add-post", "Add New Post");
    }

    private boolean isFieldValid(){
        if (txtSearch.getText().isEmpty()){
            AlertTools.handleSearchValueError();
            return false;
        }

        if (Objects.equals(cmbMethod.getValue(), "Likes") && !txtSearch.getText().matches("[0-9]+")){
            AlertTools.handleSearchValueError();
            return false;
        }

        return true;
    }

}