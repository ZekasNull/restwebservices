module se.ltu.restwebservices {
    requires jakarta.jakartaee.api;
    requires java.logging;
    requires java.sql;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    exports se.ltu.restwebservices to javafx.graphics;
    exports data_objects.canvas;
    exports client to javafx.fxml;
	opens client to javafx.fxml;
}