package data_analytics_hub;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    public static Stage staticStage;

    @Override
    public void start(Stage stage) throws IOException {
        double width = 600;
        double height = 400;

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/main-menu.fxml"));
        Rectangle2D screenBounds = javafx.stage.Screen.getPrimary().getVisualBounds();
        staticStage = stage;

        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setTitle("Data Analytics Hub");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void changeScene(String fxmlName) {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/" + fxmlName + ".fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            staticStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeTitle(String title) {
        staticStage.setTitle(title);
    }

    public static void main(String[] args) {
        launch();
    }
}