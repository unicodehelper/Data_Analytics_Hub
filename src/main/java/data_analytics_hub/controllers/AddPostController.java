package data_analytics_hub.controllers;

import data_analytics_hub.Application;
import data_analytics_hub.Session;
import data_analytics_hub.modal.Post;
import data_analytics_hub.tools.AlertTools;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddPostController {

    @FXML
    private TextField txtPostId, txtNumLikes, txtNumShares, txtAuthor;

    @FXML
    private DatePicker txtDate;

    @FXML
    private ComboBox<String> cmbHour, cmbMinute;

    @FXML
    private TextArea txtContent;

    @FXML
    void initialize() {
        initComboBox();
    }

    private void initComboBox(){
        for (int i = 0; i < 24; i++) {
            String hour = i < 10 ? "0" + i : "" + i;
            cmbHour.getItems().add(hour);
        }
        for (int i = 0; i < 60; i++) {
            String minute = i < 10 ? "0" + i : "" + i;
            cmbMinute.getItems().add(minute);
        }
    }

    private boolean isFieldValid(){
        if (txtPostId.getText().isEmpty() || txtNumLikes.getText().isEmpty() ||
                txtNumShares.getText().isEmpty() || txtAuthor.getText().isEmpty() ||
                txtDate.getValue() == null || cmbHour.getValue() == null ||
                cmbMinute.getValue() == null || txtContent.getText().isEmpty()
        ) {
            AlertTools.handleEmptyField();
            return false;
        }

        if (!txtNumLikes.getText().matches("[0-9]+") ||
                !txtNumShares.getText().matches("[0-9]+")
        ) {
            AlertTools.handleNotIntegerError();
            return false;
        }

        return true;
    }

    private boolean isPostIdExists(){
        String postId = txtPostId.getText();
        if (Session.postDAO.get(postId).isPresent()) {
            AlertTools.handlePostIdExists();
            return true;
        }
        return false;
    }

    @FXML
    void btnBackClicked() {
        Application.changeScene("dashboard", "Dashboard");
    }

    @FXML
    void btnAddPostClicked() {
        if (!isFieldValid()) return;
        if (isPostIdExists()) return;

        createNewPost();
        clearFields();
        AlertTools.handleAddPostSuccess();
        Application.changeScene("dashboard", "Dashboard");
    }

    private void createNewPost(){
        String postId = txtPostId.getText();
        String content = txtContent.getText();
        int numLikes = Integer.parseInt(txtNumLikes.getText());
        int numShares = Integer.parseInt(txtNumShares.getText());
        String author = txtAuthor.getText();
        String date = txtDate.getValue().toString();
        String[] data = date.split("-");
        date = data[2] + "/" + data[1] + "/" + data[0];

        String datetime = date + " " + cmbHour.getValue() + ":" + cmbMinute.getValue();

        Post newPost = new Post(postId, content, author, numLikes, numShares, datetime);
        Session.currentUser.addPost(newPost);
    }

    private void clearFields(){
        txtPostId.clear();
        txtContent.clear();
        txtNumLikes.clear();
        txtNumShares.clear();
        txtAuthor.clear();
        txtDate.setValue(null);
        cmbHour.setValue(null);
        cmbMinute.setValue(null);
    }

}
