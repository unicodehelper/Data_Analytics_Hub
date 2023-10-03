module data_analytics_hub {
    requires javafx.controls;
    requires javafx.fxml;


    opens data_analytics_hub to javafx.fxml;
    exports data_analytics_hub;
    opens data_analytics_hub.controllers to javafx.fxml;
    exports data_analytics_hub.controllers;
    opens data_analytics_hub.modal to javafx.fxml;
    exports data_analytics_hub.modal;
}