package data_analytics_hub.controllers;

import data_analytics_hub.Application;
import data_analytics_hub.Session;
import data_analytics_hub.modal.User;
import data_analytics_hub.modal.UserFactory;
import data_analytics_hub.tools.AlertTools;
import data_analytics_hub.tools.FxTools;
import javafx.fxml.FXML;
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
        if (checkEmptyField()) return;

        handleLogin();
    }

    private boolean checkEmptyField() {
        if (txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()) {
            AlertTools.handleEmptyField();
            return true;
        }
        return false;
    }

    @FXML
    void pwdToggleClicked() {
        FxTools.togglePwdVisible(txtPassword,txtPasswordView, pwdToggle);
    }

    private void handleLogin(){
        String username = txtUsername.getText();
        String password = txtPassword.getText() == null ? txtPasswordView.getText() : txtPassword.getText();
        User user = UserFactory.getUser(username, password);
        if (user != null) {
            Session.currentUser = user;
            AlertTools.handleLoginSuccess();
            Application.changeScene("dashboard", "Dashboard");
        } else {
            AlertTools.handleLoginFailed();
        }
    }
}
