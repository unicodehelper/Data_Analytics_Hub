package data_analytics_hub.controllers;

import data_analytics_hub.Application;
import data_analytics_hub.Session;
import data_analytics_hub.functions.Validator;
import data_analytics_hub.modal.User;
import data_analytics_hub.modal.UserFactory;
import data_analytics_hub.tools.AlertTools;
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
        if (checkEmptyField()) return;
        if (!passValidation()) return;
        handleRegister();
    }

    private boolean passValidation(){
        return checkEmail() && checkUsername() && checkPassword() && checkNames();
    }

    private boolean checkEmptyField() {
        if (txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty() ||
                txtFirstName.getText().isEmpty() || txtLastName.getText().isEmpty() ||
                txtEmail.getText().isEmpty()
        ) {
            AlertTools.handleEmptyField();
            return true;
        }
        return false;
    }

    private boolean checkEmail(){
        if (Validator.validateEmail(txtEmail.getText())){
            return true;
        }
        else{
            AlertTools.handleEmailFormatError();
            return false;
        }
    }

    private boolean checkUsername(){
        String username = txtUsername.getText();
        if (Validator.validateUsername(username)){
            if (Session.userDAO.isUsernameExist(username)){
                AlertTools.handleUsernameExistsError();
                return false;
            }
            return true;
        }
        else{
            AlertTools.handleUsernameFormatError();
            return false;
        }
    }

    private boolean checkPassword(){
        if (Validator.validatePassword(txtPassword.getText())){
            return true;
        }
        else{
            AlertTools.handlePasswordFormatError();
            return false;
        }
    }

    private boolean checkNames(){
        if (Validator.validateName(txtFirstName.getText()) && Validator.validateName(txtLastName.getText())){
            return true;
        }
        else{
            AlertTools.handleNameFormatError();
            return false;
        }
    }

    private void handleRegister(){
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String email = txtEmail.getText();
        User user = UserFactory.createUser(username, password, firstName, lastName, email);
        Session.userDAO.save(user);
        AlertTools.handleRegisterSuccess();
        Application.changeScene("main-menu", "Data Analytics Hub");
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
