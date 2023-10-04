module data_analytics_hub {
    requires javafx.controls;
    requires javafx.fxml;


    opens data_analytics_hub to javafx.fxml;
    exports data_analytics_hub;
    opens data_analytics_hub.controllers to javafx.fxml;
    exports data_analytics_hub.controllers;
    opens data_analytics_hub.modal to javafx.fxml;
    exports data_analytics_hub.modal;
    opens data_analytics_hub.functions to javafx.fxml;
    exports data_analytics_hub.functions;
    opens data_analytics_hub.tools to javafx.fxml;
    exports data_analytics_hub.tools;
    opens data_analytics_hub.dao to javafx.fxml;
    exports data_analytics_hub.dao;
}