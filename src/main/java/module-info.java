module se.ltu.restwebservices {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires jakarta.ws.rs;
    //kommentarade bort nedan rad för fick ett fel som var p.g.a. den, men låter det vara kvar ändå utifall någon inser att det behövs
    //requires se.ltu.restwebservices;


    opens se.ltu.restwebservices to javafx.fxml;
	exports se.ltu.restwebservices;
	exports client;
	opens client to javafx.fxml;
}