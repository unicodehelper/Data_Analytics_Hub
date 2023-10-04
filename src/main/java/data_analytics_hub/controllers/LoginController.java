package data_analytics_hub.controllers;

import data_analytics_hub.Application;
import data_analytics_hub.Session;
import data_analytics_hub.modal.User;
import data_analytics_hub.modal.UserFactory;
import data_analytics_hub.tools.FxTools;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField txtUsername,txtPasswordView;

    @FXML
    private RadioButton pwdToggle;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void btnBackClicked() {
        Application.changeScene("main-menu", "Data Analytics Hub");
    }

    @FXML
    void btnLoginClicked() {
        String username = txtUsername.getText();
        String password = txtPassword.getText() == null ? "" : txtPassword.getText();
        User user = UserFactory.getUser(username, password);
        if (user != null) {
            Session.currentUser = user;
            Application.changeScene("dashboard", "Dashboard");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText("Invalid username or password");
            alert.setContentText("Please try again");
            alert.showAndWait();
        }
    }

    @FXML
    void pwdToggleClicked() {
        FxTools.togglePwdVisible(txtPassword,txtPasswordView, pwdToggle);
    }
}
