package se.ltu.restwebservices;

import com.mycompany.mavenproject3.resources.DatabaseConnector;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class HelloController {
	@FXML
	ComboBox kurskodCombobox, uppgiftCombobox, modulCombobox, namnCombobox, betygCombobox;
    @FXML
    TextField datumTextfield;
    @FXML
    Button sparaButton;

    ArrayList kurskoder = new ArrayList();

    public void initialize()
    {
        kurskoder = DatabaseConnector.getKurskodFromEpok();
        for(int i=0; i<kurskoder.size();i++)
        {
            kurskodCombobox.getItems().add(kurskoder.get(i));
        }
    }

	@FXML
	protected void sparaButtonPressed()
	{

	}

}