package data_analytics_hub.tools;

import data_analytics_hub.Application;
import data_analytics_hub.Session;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class FxTools {

    public static void togglePwdVisible(PasswordField passwordField, TextField txtField, RadioButton toggle) {
        if (toggle.isSelected()) {
            toggle.setText("Hide");
            txtField.setText(passwordField.getText());
            txtField.setVisible(true);
            passwordField.setVisible(false);
        } else {
            toggle.setText("Show");
            passwordField.setText(txtField.getText());
            txtField.setVisible(false);
            passwordField.setVisible(true);
        }
    }

    public static void initFXML(Pane pane, String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/" + fxml + ".fxml"));
        pane.getChildren().clear();
        pane.getChildren().add(fxmlLoader.load());
    }

    public static File openSaveFileChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Post");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv")
        );
        fileChooser.setInitialFileName("post-" + Session.currentPost.getPostId());
        return fileChooser.showSaveDialog(Application.staticStage);
    }

    public static File openImportFileChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import Post");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv")
        );
        return fileChooser.showOpenDialog(Application.staticStage);
    }
}
