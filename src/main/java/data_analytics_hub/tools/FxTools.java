package data_analytics_hub.tools;

import data_analytics_hub.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

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
}
