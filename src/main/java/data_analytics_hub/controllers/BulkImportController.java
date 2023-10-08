package data_analytics_hub.controllers;

import data_analytics_hub.Session;
import data_analytics_hub.functions.CsvExecutor;
import data_analytics_hub.modal.Post;
import data_analytics_hub.tools.AlertTools;
import data_analytics_hub.tools.FxTools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.util.ArrayList;

public class BulkImportController {

    private final CsvExecutor csvExecutor = new CsvExecutor();
    @FXML
    private Button btnAddCollection, btnSelectFile;

    @FXML
    private TableView<Post> tableImportedPosts;

    @FXML
    private TableColumn<Post, String> colPostId, colContent, colAuthor, colDatetime;

    @FXML
    private TableColumn<Post, Integer> colShares, colLikes;

    private ArrayList<Post> postList = null;

    @FXML
    void btnSelectFileClicked() {
        File file = FxTools.openImportFileChooser();
        if (file != null) {
            handleCsvImported(file);
        }
    }

    @FXML
    void btnAddCollectionClicked() {
        if (isPostListValid() && !isPostIdsExist()) {
            for (Post post : postList) {
                Session.currentUser.addPost(post);
            }
            AlertTools.handleAddPostSuccess();
            switchToUserCollections();
        } else {
            AlertTools.handleAddPostFailed();
        }
    }

    private void handleCsvImported(File file) {
        postList = csvExecutor.convertToPostList(file);
        if (postList == null) {
            AlertTools.handleImportCsvFormatError();
            return;
        }
        ObservableList<Post> data = FXCollections.observableArrayList(postList);
        colPostId.setCellValueFactory(new PropertyValueFactory<>("PostId"));
        colContent.setCellValueFactory(new PropertyValueFactory<>("Content"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("Author"));
        colLikes.setCellValueFactory(new PropertyValueFactory<>("Likes"));
        colShares.setCellValueFactory(new PropertyValueFactory<>("Shares"));
        colDatetime.setCellValueFactory(new PropertyValueFactory<>("Datetime"));
        tableImportedPosts.setItems(data);
        btnAddCollection.setDisable(false);
        btnSelectFile.setDisable(true);
    }

    private boolean isPostListValid() {
        return postList != null && postList.size() > 0;
    }

    private boolean isPostIdsExist() {
        String[] postIds = postList.stream().map(Post::getPostId).toArray(String[]::new);
        if (Session.postDAO.isPostExist(postIds)) {
            AlertTools.handlePostIdExists();
            return true;
        }
        return false;
    }

    private void switchToUserCollections() {
        try {
            FxTools.initFXML(Session.dashboardPane, "user-collections");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
