package data_analytics_hub.controllers;

import data_analytics_hub.Application;
import data_analytics_hub.Session;
import data_analytics_hub.functions.CsvExecutor;
import data_analytics_hub.modal.Post;
import data_analytics_hub.tools.AlertTools;
import data_analytics_hub.tools.FxTools;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;

public class ViewPostController {

    @FXML
    private TextField txtPostID, txtLikes, txtAuthor, txtShares, txtDatetime;

    @FXML
    private TextArea txtContent;

    @FXML
    void initialize() {
        initTextField();
    }

    private void initTextField(){
        Post currentPost = Session.currentPost;
        txtPostID.setText(currentPost.getPostId());
        txtContent.setText(currentPost.getContent());
        txtLikes.setText(String.valueOf(currentPost.getLikes()));
        txtShares.setText(String.valueOf(currentPost.getShares()));
        txtAuthor.setText(currentPost.getAuthor());
        txtDatetime.setText(currentPost.getDatetime());
    }

    @FXML
    void btnCloseClicked() {
        try {
            FxTools.initFXML(Session.dashboardPane, "user-collections");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnExportClicked() {
        File file = FxTools.openSaveFileChooser();
        if (file != null) {
            Session.csvExecutor.exportPostToCsv(file, Session.currentPost);
            AlertTools.handleExportPostSuccess();
        }
    }

    @FXML
    void btnDeleteClicked() {
        boolean delete = AlertTools.handleDeletePost();
        if (delete) {
            Session.currentUser.removePost(Session.currentPost.getPostId());
            try {
                AlertTools.handleDeletePostSuccess();
                FxTools.initFXML(Session.dashboardPane, "user-collections");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
