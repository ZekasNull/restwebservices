package se.ltu.restwebservices;

import com.mycompany.mavenproject3.resources.*;
import com.mycompany.mavenproject3.resources.DatabaseConnector;
import data_objects.Ladok_Resultat;
import data_objects.Studentits_Student;
import data_objects.canvas.Canvas_Kursuppgift;
import data_objects.canvas.Canvas_StudentBetygDTO;
import data_objects.epok.Epok_Modul;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class HelloController {
	@FXML
	ComboBox kurskodCombobox, uppgiftCombobox, namnCombobox, betygCombobox;
    @FXML
    TextField datumTextfield;
    @FXML
    Button sparaButton;
    @FXML
    Label omdömeLabel, resultatLabel;

    //variabler
    ArrayList kurskoder = new ArrayList();
    ArrayList<Canvas_Kursuppgift> kursuppgifter = new ArrayList();
    ArrayList<Epok_Modul> moduler = new ArrayList();
    List<Canvas_StudentBetygDTO> studentbetyg = new ArrayList<>();
    int valdModul = -1;
    Studentits_Student student = new Studentits_Student();

    //utförs vid start av programmet
    public void initialize()
    {
        kurskoder = DatabaseConnector.getKurskodFromEpok();
        for(int i=0; i<kurskoder.size();i++)
        {
            kurskodCombobox.getItems().add(kurskoder.get(i));
        }
    }

	@FXML
	protected void sparaButtonPressed() throws ParseException {
        Ladok_Resultat resultat = new Ladok_Resultat();
        //sätter variabler i resultatet
        resultat.setBetyg(betygCombobox.getValue().toString());
        resultat.setKurskod(kurskodCombobox.getValue().toString());
        resultat.setModulkod(valdModul);
        //jättemånga rader för att få till datumet i sql
        SimpleDateFormat datumFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDatum = datumFormat.parse(datumTextfield.getText().toString());
        java.sql.Date sqlDatum = new java.sql.Date(utilDatum.getTime());
        resultat.setDatum(sqlDatum);
        //jättemånga rader för att få till personnumret
        String namn = namnCombobox.getValue().toString();
        String användare = "";
        for(int i=0; i<studentbetyg.size();i++)
        {
            if(studentbetyg.get(i).getStudentNamn().equals(namn))
            {
                användare = studentbetyg.get(i).getAnvändare();
            }
        }
        student = StudentItsResource.getStudent(användare);
        resultat.setPersonnummer(student.getPersonnummer());

        //och nu kan vi faktiskt läsa in resultatet
        //OBS detta är den del som ger ett exception, men det laddar upp resultatet till databasen ändå??
        //Har testat andra varianter t.ex. att bara skriva LadokResource.registerResult(resultat); men det ger också exception :(
        Response r = LadokResource.registerResult(resultat);
        if(r.getStatus()!=200)
        {
            resultatLabel.setText("Lyckades");
        }
        else resultatLabel.setText("Misslyckades");
	}

    //när kurskod väljs
    @FXML
    protected void kurskodComboboxAction()
    {
        uppgiftCombobox.getItems().clear();

        //fyller comboboxen med uppgifter som finns tillgängliga i vald kurs
        kursuppgifter = DatabaseConnector.getKursuppgifterFromCanvas(kurskodCombobox.getValue().toString());
        for(int i=0; i<kursuppgifter.size();i++)
        {
            uppgiftCombobox.getItems().add(kursuppgifter.get(i).getUppgiftsnamn());
        }
    }

    //när uppgift väljs
    @FXML
    protected void uppgiftComboboxAction()
    {
        namnCombobox.getItems().clear();
        betygCombobox.getItems().clear();

        //fyller comboboxen med betyg som är tillgängliga för vald uppgift
        //U och G finns för alla, sen görs en kontroll ifall uppgiftens betygsskala är VG så läggs det till också
        betygCombobox.getItems().add("U");
        betygCombobox.getItems().add("G");
        String valdUppgift = uppgiftCombobox.getValue().toString();
        moduler = EpokResource.getModul(kurskodCombobox.getValue().toString());
        for(int i=0; i<moduler.size();i++)
        {
            if(moduler.get(i).getModulnamn().equals(valdUppgift) && moduler.get(i).getKurskod().equals(kurskodCombobox.getValue().toString())
            && moduler.get(i).getBetygsskala().equals("VG"))
            {
                betygCombobox.getItems().add("VG");
                //nedan rad plockar fram modulkoden som behövs för nästa anrop till getCanvasGrades
                valdModul = moduler.get(i).getModulkod();
            }
            else if(moduler.get(i).getModulnamn().equals(valdUppgift) && moduler.get(i).getKurskod().equals(kurskodCombobox.getValue().toString())
                && moduler.get(i).getBetygsskala().equals("G"))
            {
                //nedan rad plockar fram modulkoden som behövs för nästa anrop till getCanvasGrades
                valdModul = moduler.get(i).getModulkod();
            }
        }

        //fyller comboboxen med namn på alla som läser kursen
        studentbetyg = CanvasResource.getCanvasGrades(kurskodCombobox.getValue().toString(), valdModul);
        for(int i=0; i<studentbetyg.size();i++)
        {
            namnCombobox.getItems().add(studentbetyg.get(i).getStudentNamn());
        }
    }

    //när namn väljs
    @FXML
    protected void namnComboboxAction()
    {
        //skriver ut betyg som är satt i Canvas
        int selected = namnCombobox.getSelectionModel().getSelectedIndex();
        String betyg = studentbetyg.get(selected).getBetyg();
        omdömeLabel.setText(betyg);
    }

}