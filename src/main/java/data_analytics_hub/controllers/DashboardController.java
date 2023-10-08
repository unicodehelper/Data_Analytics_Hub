package data_analytics_hub.controllers;

import data_analytics_hub.Application;
import data_analytics_hub.Session;
import data_analytics_hub.tools.AlertTools;
import data_analytics_hub.tools.FxTools;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class DashboardController {

    @FXML
    private Label lblUsername, lblFullName, lblPlan;

    @FXML
    private Pane dashboardPane, noticePane;

    @FXML
    private Button btnUpgradePlan, btnBulkImport, btnPostsAnalytics;

    @FXML
    void initialize(){
        Session.dashboardPane = dashboardPane;
        initWelcomeUser();
        initPane();
        showWelcomeNotice();
        handleUserPlan();
    }

    private void initWelcomeUser(){
        lblUsername.setText(Session.currentUser.getUsername());
    }

    private void initPane(){
        switchPane("user-collections");
    }

    private void showWelcomeNotice(){
        if(!Session.isWelcomeNoticed){
            noticePane.setVisible(true);
            lblFullName.setText(Session.currentUser.getFullName());
            Session.isWelcomeNoticed = true;
            //set a timer to hide the noticePane
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                noticePane.setVisible(false);
            }).start();
        }
    }

    private void handleUserPlan(){
        if (Session.currentUser.getIsVip()) {
            lblPlan.setText("VIP");
            btnUpgradePlan.setVisible(false);
            btnBulkImport.setDisable(false);
            btnPostsAnalytics.setDisable(false);
        } else {
            lblPlan.setText("Free");
            btnUpgradePlan.setVisible(true);
        }
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
    void btnUpgradeClicked() {
        boolean isUpgrade = AlertTools.handleUpgradePlan();
        if (isUpgrade) {
            Session.currentUser.setIsVip(true);
            AlertTools.handleUpgradePlanSuccess();
            userLogout();
        }
    }

    @FXML
    void btnBulkImportClicked() {
        switchPane("bulk-import");
    }

    @FXML
    void btnPostsAnalyticsClicked() {
        switchPane("posts-analytics");
    }

    @FXML
    void btnLogoutClicked() {
        if (AlertTools.handleLogout()) {
            userLogout();
        }
    }

    private void userLogout(){
        Session.currentUser = null;
        Session.currentPost = null;
        Session.isWelcomeNoticed = false;
        Application.changeScene("main-menu", "Data Analytics Hub");
    }

}
