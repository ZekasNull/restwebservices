module se.ltu.restwebservices {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;


	opens se.ltu.restwebservices to javafx.fxml;
	exports se.ltu.restwebservices;
}