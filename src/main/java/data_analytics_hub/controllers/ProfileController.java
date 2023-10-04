package data_analytics_hub.controllers;

import data_analytics_hub.Session;
import data_analytics_hub.functions.Encryption;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ProfileController {

    @FXML
    private TextField txtUsername, txtLastName, txtFirstName, txtPassword;

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
    }

    @FXML
    void btnUpdateClicked() {

    }

}
