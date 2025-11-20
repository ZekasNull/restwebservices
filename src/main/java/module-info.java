module se.ltu.restwebservices {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires jakarta.ws.rs;


	opens se.ltu.restwebservices to javafx.fxml;
	exports se.ltu.restwebservices;
}