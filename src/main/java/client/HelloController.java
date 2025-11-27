package client;

import data_objects.canvas.Canvas_Betyg;
import data_objects.canvas.Canvas_Student;
import data_objects.Ladok_Resultat;
import data_objects.Studentits_Student;
import data_objects.canvas.Canvas_Kursuppgift;
import data_objects.canvas.Canvas_StudentBetygDTO;
import data_objects.epok.Epok_Modul;

import database.DatabaseResponse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import webservices.EpokResource;

public class HelloController {
    private final static Logger LOG = Logger.getLogger(HelloController.class.getName());
    @FXML
    ComboBox<String> kurskodCombobox, betygCombobox;
    @FXML
    ComboBox<Canvas_Student> namnCombobox;
    @FXML
    ComboBox<Canvas_Kursuppgift> uppgiftCombobox;
    @FXML
    TextField datumTextfield;
    @FXML
    Button sparaButton;
    @FXML
    Label omdömeLabel, resultatLabel;

    //variabler
    ObservableList<String> kurskoder = FXCollections.observableArrayList();
    ArrayList<Canvas_Kursuppgift> kursuppgifter = new ArrayList<>();
    List<Epok_Modul> moduler = new ArrayList<>();
    List<Canvas_StudentBetygDTO> studentbetyg = new ArrayList<>();
    int valdModul = -1;
    Studentits_Student student = new Studentits_Student();

    //tjänst
    NetworkService ns;
    //LadokResource lr = new LadokResource();

    /**
     * Initialiserar klassen. Följande sker:
     * Nätverkstjänst skapas
     * Alla kurskoder läses in från EPoK (borde väl egentligen vara Canvas men sak samma)
     * Cellfabrik för lista och knapp sätts för uppgifter.
     * Ovanstående för namncombobox.
     */
    public void initialize()
    {
        ns = new NetworkService();
        //läs in alla kurskoder
        kurskoder.addAll(ns.getAllKurskod());
        kurskodCombobox.setItems(kurskoder);

        //förbered comboboxes
        uppgiftCombobox.setCellFactory(param -> new ListCell<Canvas_Kursuppgift>() {
            @Override
            protected void updateItem(Canvas_Kursuppgift item, boolean empty)
            {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getUppgiftsnamn());
            }
        });
        //själva knapp-cellen (när den är ihopfälld) måste ha en egen
        uppgiftCombobox.setButtonCell(new ListCell<Canvas_Kursuppgift>() {
            @Override
            protected void updateItem(Canvas_Kursuppgift item, boolean empty)
            {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getUppgiftsnamn());
            }
        });

        //samma som ovan fast för namncombobox
        namnCombobox.setCellFactory(param -> new ListCell<Canvas_Student>() {
            @Override
            protected void updateItem(Canvas_Student item, boolean empty)
            {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNamn());
            }
        });

        namnCombobox.setButtonCell(new ListCell<Canvas_Student>() {
            @Override
            protected void updateItem(Canvas_Student item, boolean empty)
            {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNamn());
            }
        });
    }

    @FXML
    protected void sparaButtonPressed() throws ParseException
    {
        boolean result;
        //När knappen trycks ska ett resultat byggas och sedan skickas till nätverkstjänsten
        Ladok_Resultat resultat = prepareLadokResult();
        DatabaseResponse dr = ns.sendLadokResultat(resultat, namnCombobox.getValue().getAnvändare());
        //egentligen r.getStatus() == 200
        if (dr.isSuccess())
        {
            resultatLabel.setText("Lyckades");
        } else if (dr.getErrorCode() == 1062)
        {
            resultatLabel.setText("Misslyckades");
            showErrorPopup("Nekad: Studenten har redan ett registrerat resultat för den uppgiften.");
        } else
        {
            resultatLabel.setText("Misslyckades");
            showErrorPopup(dr.toString());
        }
    }

    /**
     * Hjälpmetod för sparaButtonPressed
     *
     * @return
     * @throws ParseException
     */
    private Ladok_Resultat prepareLadokResult() throws ParseException
    {
        Ladok_Resultat resultat = new Ladok_Resultat();

        // Set basic fields
        resultat.setBetyg(betygCombobox.getValue());
        resultat.setKurskod(kurskodCombobox.getValue());
        resultat.setModulkod(valdModul);

        // Parse and set date
        SimpleDateFormat datumFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDatum = datumFormat.parse(datumTextfield.getText());
        java.sql.Date sqlDatum = new java.sql.Date(utilDatum.getTime());
        resultat.setDatum(sqlDatum);

        return resultat;
    }

    //när kurskod väljs
    @FXML
    protected void kurskodComboboxAction()
    {
        uppgiftCombobox.getItems().clear();

        //fyller comboboxen med uppgifter som finns tillgängliga i vald kurs
        kursuppgifter.addAll(ns.getAllKursuppgift(kurskodCombobox.getValue()));
        for (Canvas_Kursuppgift canvasKursuppgift : kursuppgifter)
        {
            uppgiftCombobox.getItems().add(canvasKursuppgift);
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
        String valdUppgift = uppgiftCombobox.getValue().getUppgiftsnamn();
        moduler = EpokResource.getModul(kurskodCombobox.getValue());
        for (Epok_Modul epokModul : moduler)
        {
            if (epokModul.getModulnamn().equals(valdUppgift) && epokModul.getKurskod().equals(kurskodCombobox.getValue())
                    && epokModul.getBetygsskala().equals("VG"))
            {
                betygCombobox.getItems().add("VG");
                //nedan rad plockar fram modulkoden som behövs för nästa anrop till getCanvasGrades
                valdModul = epokModul.getModulkod();
            } else if (epokModul.getModulnamn().equals(valdUppgift) && epokModul.getKurskod().equals(kurskodCombobox.getValue())
                    && epokModul.getBetygsskala().equals("G"))
            {
                //nedan rad plockar fram modulkoden som behövs för nästa anrop till getCanvasGrades
                valdModul = epokModul.getModulkod();
            }
        }

        //hjälpmetod hämtar betyg och fyller comboboxen med namn på alla som läser kursen
        populateUsersBox(kurskodCombobox.getValue(), uppgiftCombobox.getValue().getUppgiftNr());
    }

    private void populateUsersBox(String kurskod, int kursuppgift)
    {
        ObservableList<Canvas_Student> studenter = FXCollections.observableArrayList();
        studenter.addAll(ns.getCanvasStudents());
        namnCombobox.setItems(studenter);
    }

    //när namn väljs
    @FXML
    protected void namnComboboxAction()
    {
        //skriver ut betyg som är satt i Canvas
        Canvas_Student student = namnCombobox.getSelectionModel().getSelectedItem();
        int uppgiftnr = uppgiftCombobox.getValue().getUppgiftNr();
        Canvas_Betyg cb = ns.getCanvasGrade(student.getAnvändare(), uppgiftnr);
        omdömeLabel.setText(cb.getBetyg());
    }

    /**
     * Hjälpmetod för att visa felmeddelanden.
     *
     * @param message Meddelandet som ska visas
     */
    private void showErrorPopup(String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fel");
        alert.setHeaderText(message);
        alert.showAndWait();
    }


}