package client;

import data_objects.Ladok_Resultat;
import data_objects.Studentits_Student;
import data_objects.canvas.*;
import data_objects.epok.Epok_Modul;
import database.DatabaseConnector;
import database.DatabaseResponse;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Ansvarar för klientens nätverkskommunikation. Kan behöva delas upp i fler klasser (då enligt tjänst/API som anropas).
 */
public class NetworkService {
    private static final Logger LOG = Logger.getLogger(NetworkService.class.getName());
    private DatabaseConnector db = new DatabaseConnector(); //TODO endast temporärt, ska använda http-klienter
    private String baseUrl;

    //region constructors
    public NetworkService()
    {

    }

    public NetworkService(String baseUrl)
    {
        this.baseUrl = baseUrl;
    }
    //endregion

    /**
     * Skickar ett resultat till Ladok för registrering. Måste göra sekundära anrop innan databas.
     *
     * @param resultat         Ett objekt med kurskod, modulkod, betyg och datum
     * @param studentanvändare studentanvändare (abcdef-1) för den som ska registreras
     * @return True om det lyckades, annars false
     * //FIXME should return Response
     */
    public DatabaseResponse sendLadokResultat(Ladok_Resultat resultat, String studentanvändare)
    {
        Studentits_Student student = db.getStudentFromStudentits(studentanvändare);
        resultat.setPersonnummer(student.getPersonnummer());

        return db.InsertNewLadokResult(resultat);
        //TODO proper HTTP call to ladok endpoint
		/*
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(baseUrl + "/restwebservices/ladok/regresultat");

		Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(resultat, MediaType.APPLICATION_JSON));

		return response;
		 */
    }

    public List<Canvas_StudentBetygDTO> getStudentsAndGrades(String kurskod, int kursuppgift)
    {

        return db.getCanvasGrades(kurskod, kursuppgift);
        //TODO proper HTTP call to canvas ws endpoint
    }

    //FIXME egentligen studerar inte alla studenter alla kurser
    public List<Canvas_Student> getCanvasStudents()
    {
        //TODO som HTTP
        return db.getCanvasStudents();
    }

    public Canvas_Betyg getCanvasGrade(String användare, int uppgift_nr)
    {
        //TODO ska ske med http som vanligt
        return db.getCanvasGrade(användare, uppgift_nr);
    }

    public List<String> getAllKurskod()
    {
        //TODO http, you guessed it
        return db.getKurskodFromEpok();
    }

    public List<Canvas_Kursuppgift> getAllKursuppgift(String kurskod)
    {
        //TODO the same as before
        return db.getKursuppgifterFromCanvas(kurskod);
    }

    /**
     * @param kurskod
     * @return
     */
    public List<Epok_Modul> getModulFromEpok(String kurskod)
    {
        return db.getModulesFromEpok(kurskod);
    }


}
