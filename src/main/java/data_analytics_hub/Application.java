package data_analytics_hub;

import data_analytics_hub.modal.UserFactory;
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
        double height = 450;

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/main-menu.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/dashboard.fxml"));
        Rectangle2D screenBounds = javafx.stage.Screen.getPrimary().getVisualBounds();
        staticStage = stage;

        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setTitle("Data Analytics Hub");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        staticStage.setOnCloseRequest(e -> Session.save());  //handle stage close
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

    public static void changeScene(String fxmlName, String title) {
        staticStage.setTitle(title);
        changeScene(fxmlName);
    }

    public static void main(String[] args) {
        Session.execute();
//        Session.currentUser = UserFactory.getUser("tester", "test123");
        launch();
    }
}