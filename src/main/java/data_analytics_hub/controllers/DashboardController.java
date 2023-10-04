package data_analytics_hub.controllers;

import data_analytics_hub.Application;
import data_analytics_hub.Session;
import data_analytics_hub.tools.AlertTools;
import data_analytics_hub.tools.FxTools;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class DashboardController {

    @FXML
    private Label lblUsername;

    @FXML
    private Pane dashboardPane;

    @FXML
    void initialize(){
        initWelcomeUser();
        initPane();
    }

    private void initWelcomeUser(){
        lblUsername.setText(Session.currentUser.getUsername());
    }

    private void initPane(){
        switchPane("user-collections");
    }

    private void switchPane(String fxmlName) {
        try {
            FxTools.initFXML(dashboardPane, fxmlName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnProfileClicked() {
        dashboardPane.getChildren().clear();
        switchPane("profile");
    }

    @FXML
    void btnCollectionClicked() {
        switchPane("user-collections");
    }

    @FXML
    void btnLogoutClicked() {
        if (AlertTools.handleLogout()) {
            Session.currentUser = null;
            Application.changeScene("main-menu", "Data Analytics Hub");
        }
    }

}
