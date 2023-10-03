package data_analytics_hub.controllers;

import data_analytics_hub.Application;
import javafx.fxml.FXML;

public class MainMenuController {
    @FXML
    void btnRegisterClicked() {
        Application.changeScene("register");
        Application.changeTitle("Register");
    }

    @FXML
    void btnLoginClicked() {
        Application.changeScene("login");
        Application.changeTitle("Login");
    }

    @FXML
    void btnExitClicked() {
        System.exit(0);
    }
}
