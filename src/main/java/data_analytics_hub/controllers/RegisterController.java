package data_analytics_hub.controllers;

import data_analytics_hub.Application;
import data_analytics_hub.tools.FxTools;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML
    private TextField txtUsername, txtPasswordView, txtLastName, txtFirstName, txtEmail;

    @FXML
    private RadioButton pwdToggle;

    @FXML
    private PasswordField txtPassword;


    @FXML
    void btnRegisterClicked() {

    }

    @FXML
    void pwdToggleClicked() {
        FxTools.togglePwdVisible(txtPassword, txtPasswordView, pwdToggle);
    }

    @FXML
    void btnBackClicked() {
        Application.changeScene("main-menu", "Data Analytics Hub");
    }
}
