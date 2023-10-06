package data_analytics_hub.controllers;

import data_analytics_hub.Session;
import data_analytics_hub.functions.Encryption;
import data_analytics_hub.functions.Validator;
import data_analytics_hub.tools.AlertTools;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.Objects;

public class ProfileController {

    @FXML
    private TextField txtUsername, txtLastName, txtFirstName, txtPassword, txtEmail;

    @FXML
    private Button btnUpdate;

    @FXML
    void initialize(){
        initUserDetails();
    }

    private void initUserDetails(){
        txtUsername.setText(Session.currentUser.getUsername());
        txtFirstName.setText(Session.currentUser.getFirstName());
        txtLastName.setText(Session.currentUser.getLastName());
        String password = Session.currentUser.getPassword();
        txtPassword.setText(new Encryption().AESDecrypt(password));
        txtEmail.setText(Session.currentUser.getEmail());
    }

    @FXML
    void btnUpdateClicked() {
        if (!isFieldNotEmpty()) return;
        if (!isFieldValid()) return;
        if (isUsernameExist(txtUsername.getText())) {
            AlertTools.handleUsernameExistsError();
            return;
        }
        updateUserDetails();
    }

    private boolean isFieldNotEmpty(){
        return !txtUsername.getText().isEmpty() &&
                !txtFirstName.getText().isEmpty() &&
                !txtLastName.getText().isEmpty() &&
                !txtPassword.getText().isEmpty();
    }

    private boolean isFieldValid(){
        return Validator.validateUsername(txtUsername.getText()) &&
                Validator.validateName(txtFirstName.getText()) &&
                Validator.validateName(txtLastName.getText()) &&
                Validator.validatePassword(txtPassword.getText()) &&
                Validator.validateEmail(txtEmail.getText());
    }

    private boolean isUsernameExist(String username){
        return Session.users.stream().anyMatch(
                user -> !Objects.equals(user.getUsername(), Session.currentUser.getUsername()) &&
                        Objects.equals(user.getUsername(), username)
        );
    }

    private void updateUserDetails(){
        Session.currentUser.setUsername(txtUsername.getText());
        Session.currentUser.setFirstName(txtFirstName.getText());
        Session.currentUser.setLastName(txtLastName.getText());
        Session.currentUser.setPassword(new Encryption().AESEncrypt(txtPassword.getText()));
        Session.currentUser.setEmail(txtEmail.getText());
        Session.userDAO.update(Session.currentUser);
        AlertTools.handleUpdateSuccess();
    }

}
