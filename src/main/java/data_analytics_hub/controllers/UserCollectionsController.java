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

    @FXML
    void initialize(){
        initTable();
        initComboBox();
        initButtonDisable();
        initDataRowListener();
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

        tableCollection.setItems(data);
    }

    private void initComboBox(){
        cmbMethod.getItems().addAll(searchMethods.keySet());
        //set default value
        cmbMethod.setValue("PostID");
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

    @FXML
    void btnSearchClicked() {
        if (!checkField()) {
            // TODO: search by likes, postID
            System.out.println("Search by " + cmbMethod.getValue() + ": " + txtSearch.getText());
        }
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

    private boolean checkField(){
        if (txtSearch.getText().isEmpty()){
            AlertTools.handleSearchValueError();
            return true;
        }

        if (Objects.equals(cmbMethod.getValue(), "Likes") && !txtSearch.getText().matches("[0-9]+")){
            AlertTools.handleSearchValueError();
            return true;
        }

        return false;
    }

}