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
import jakarta.ws.rs.core.GenericType;
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
    private final Client client;
    private final WebTarget api;
    private DatabaseConnector db = new DatabaseConnector(); //TODO endast temporärt, ska använda http-klienter
    private String baseUrl;

    //region constructors

    /**
     * Parameterlös konstruktor - antar given basURL
     * Konfigurera i din GlassFish deployment
     */
    public NetworkService()
    {
        this("http://localhost:8080/restwebservices/api"); // ändra om ni har annan bas-url
    }

    public NetworkService(String baseUrl)
    {
        this.client = ClientBuilder.newClient();
        this.api = client.target(baseUrl);
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
    // --- EXEMPEL: Skicka resultat till Ladok (POST) ---
    public boolean sendLadokResultat(Ladok_Resultat resultat, String studentanvändare)
    {
        LOG.info("sendLadokResultat called");
        // 1) hämta student från StudentITS
        Studentits_Student student = api
                .path("studentIts")
                .path("student")
                .queryParam("Användare", studentanvändare)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(Studentits_Student.class);

        if (student == null || student.getPersonnummer() == null)
        {
            return false;
        }
        LOG.info("Got student with pnr " + student.getPersonnummer());

        resultat.setPersonnummer(student.getPersonnummer());

        // 2) skicka resultatet vidare till Ladok
        Response response = api
                .path("ladok")
                .path("regResultat")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(resultat, MediaType.APPLICATION_JSON_TYPE));

        boolean ok = response.getStatus() >= 200 && response.getStatus() < 300;
        response.close();

        LOG.info("sendLadokResultat finished, code was " + response.getStatus());
        return ok;
    }

    // --- EXEMPEL: Hämta Canvas-betyg (GET) ---
    public List<Canvas_StudentBetygDTO> getStudentsAndGrades(String kurskod, int kursuppgift)
    {

        return api.path("canvas")
                .path("betyg")
                .queryParam("kurskod", kurskod)
                .queryParam("uppgiftNr", kursuppgift)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<Canvas_StudentBetygDTO>>() {
                });

    }

    //FIXME egentligen studerar inte alla studenter alla kurser
    // --- EXEMPEL: Hämta Canvas-studenter (GET) ---
    public List<Canvas_Student> getCanvasStudents()
    {

        return api.path("canvas")
                .path("studenter")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<Canvas_Student>>() {
                });

    }

    // --- EXEMPEL: Hämta en students betyg (GET) ---
    public Canvas_Betyg getCanvasGrade(String användare, int uppgift_nr)
    {
        LOG.info("Called getCanvasGrade called with params " + användare + " and " + uppgift_nr);
        return api.path("canvas")
                .path("betyg")
                .path(användare)
                .path(String.valueOf(uppgift_nr))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(Canvas_Betyg.class);

    }

    // --- EXEMPEL: Hämta kurskoder (GET) ---
    public List<String> getAllKurskod()
    {
        LOG.info("getAllKurskod() called");
        try
        {
            return
                    api.path("epok")
                            .path("kurser")
                            .request(MediaType.APPLICATION_JSON_TYPE)
                            .get(new GenericType<List<String>>() {
                            });
        } catch (Exception e)
        {
            LOG.log(Level.SEVERE, "getAllKurskod() failed");
            throw e;
        }
    }

    // --- EXEMPEL: Hämta kursuppgifter (GET) ---
    public List<Canvas_Kursuppgift> getAllKursuppgift(String kurskod)
    {
        LOG.info("getAllKursuppgift() called with parameter " + kurskod);

        return api.path("canvas")
                .path("kursuppgifter")
                .queryParam("kurskod", kurskod)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<Canvas_Kursuppgift>>() {
                });

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
