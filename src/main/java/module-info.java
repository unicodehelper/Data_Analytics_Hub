module com.example.data_analytics_hub {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.data_analytics_hub to javafx.fxml;
    exports com.example.data_analytics_hub;
    opens com.example.data_analytics_hub.controllers to javafx.fxml;
    exports com.example.data_analytics_hub.controllers;
}