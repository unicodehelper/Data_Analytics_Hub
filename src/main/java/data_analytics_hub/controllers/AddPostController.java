package data_analytics_hub.controllers;

import data_analytics_hub.Application;
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
    private ComboBox<Integer> cmbHour, cmbMinute;

    @FXML
    private TextArea txtContent;

    @FXML
    void initialize() {
        initComboBox();
    }

    private void initComboBox(){
        for (int i = 0; i < 24; i++) {
            cmbHour.getItems().add(i);
        }
        for (int i = 0; i < 60; i++) {
            cmbMinute.getItems().add(i);
        }
    }

    @FXML
    void btnBackClicked() {
        Application.changeScene("dashboard", "Dashboard");
    }

    @FXML
    void btnAddPostClicked() {

    }

}
