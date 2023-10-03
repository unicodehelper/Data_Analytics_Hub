package data_analytics_hub.controllers;

import data_analytics_hub.Application;
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
        if (pwdToggle.isSelected()) {
            pwdToggle.setText("Hide");
            txtPasswordView.setText(txtPassword.getText());
            txtPasswordView.setVisible(true);
            txtPassword.setVisible(false);
        } else {
            pwdToggle.setText("Show");
            txtPassword.setText(txtPasswordView.getText());
            txtPasswordView.setVisible(false);
            txtPassword.setVisible(true);
        }
    }

    @FXML
    void btnBackClicked() {
        Application.changeScene("main-menu");
        Application.changeTitle("Data Analytics Hub");
    }
}
