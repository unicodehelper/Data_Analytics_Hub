package data_analytics_hub.tools;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertTools {

    public static Alert alert = null;

    public static void handleEmptyField() {
        showAlert("Empty Field", "Please fill in all fields", Alert.AlertType.ERROR);
    }

    public static void handleLoginFailed() {
        showAlert("Login Error", "Invalid username or password", Alert.AlertType.ERROR);
    }

    public static void handleRegisterSuccess() {
        showAlert("Register Success", "Account successfully registered", Alert.AlertType.INFORMATION);
    }

    public static void handlePostIDExists() {
        showAlert("Post ID Error", "Post ID already exists", Alert.AlertType.ERROR);
    }

    public static void handleUsernameFormatError() {
        showAlert("Username Format Error", "Username must only contain letters and numbers", Alert.AlertType.ERROR);
    }

    public static void handleUsernameExistsError() {
        showAlert("Username Exists Error", "Username already exists", Alert.AlertType.ERROR);
    }

    public static void handleNameFormatError() {
        showAlert("Name Format Error", "Name must only contain letters", Alert.AlertType.ERROR);
    }

    public static void handleEmailFormatError() {
        showAlert("Email Format Error", "Please enter a valid email", Alert.AlertType.ERROR);
    }

    public static void handlePasswordFormatError() {
        showAlert("Password Format Error", "Password must be at least 8 characters long", Alert.AlertType.ERROR);
    }

    public static void handleLoginSuccess() {
        showAlert("Login Success", "Welcome to Data Analytics Hub", Alert.AlertType.INFORMATION);
    }

    public static boolean handleLogout() {
        showAlert("Logout", "Are you sure you want to logout?", Alert.AlertType.CONFIRMATION);
        return alert.getResult().getText().equals("OK");
    }

    public static void handleSearchValueError() {
        showAlert("Search Value Error", "Please enter a valid search value", Alert.AlertType.ERROR);
    }

    public static void handleNotIntegerError() {
        showAlert("Not Integer Error", "Please enter a valid number", Alert.AlertType.ERROR);
    }

    public static void handleAddPostSuccess() {
        showAlert("Add Post Success", "Post successfully added", Alert.AlertType.INFORMATION);
    }

    public static void handlePostIdExists() {
        showAlert("Post ID Error", "Post ID already exists", Alert.AlertType.ERROR);
    }

    public static boolean handleDeletePost() {
        showAlert("Delete Post", "Are you sure you want to delete this post?", Alert.AlertType.CONFIRMATION);
        return alert.getResult().getText().equals("OK");
    }

    public static void handleDeletePostSuccess() {
        showAlert("Delete Post Success", "Post successfully deleted", Alert.AlertType.INFORMATION);
    }

    public static void handleUpdateSuccess() {
        showAlert("User Details Update Success", "User details successfully updated", Alert.AlertType.INFORMATION);
    }

    public static void handleExportPostSuccess() {
        showAlert("Export Post Success", "Post successfully exported", Alert.AlertType.INFORMATION);
    }

    public static boolean handleUpgradePlan(){
        showAlert("Upgrade Plan", "Would you like to subscribe to the application for a monthly fee of $0?", Alert.AlertType.CONFIRMATION);
        return alert.getResult().getText().equals("OK");
    }

    public static void handleUpgradePlanSuccess(){
        showAlert("Upgrade Plan Success", "Please log out and log in again to access VIP functionalities.", Alert.AlertType.INFORMATION);
    }

    public static void showAlert(String title, String message, Alert.AlertType type) {
        alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
