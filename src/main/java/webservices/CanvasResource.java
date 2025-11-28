package webservices;

import data_objects.canvas.Canvas_Kursuppgift;
import data_objects.canvas.Canvas_Student;
import data_objects.canvas.Canvas_StudentBetygDTO;
import database.DatabaseConnector;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.logging.Logger;

@Path("canvas")
public class CanvasResource {
	private final Logger LOG = Logger.getLogger(CanvasResource.class.getName());
	private final static DatabaseConnector db = new DatabaseConnector();

	//denna borde ta fram en lista med anändare, namn, kurskod, kursnamn, uppgiftsnummer, uppgiftsnamn och betyg
	//baserat på kurskod och uppgift
	@GET
	@Path("grades/{kurskod}/{uppgift}")
	@Produces(MediaType.APPLICATION_JSON)
	public static List<Canvas_StudentBetygDTO> getCanvasGrades(
            @PathParam("kurskod") String kurskod,
            @PathParam("uppgift") int uppgift)
	{

		return db.getCanvasGrades(kurskod, uppgift);
	}

	@GET
	@Path("kursuppgifter")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Canvas_Kursuppgift> getKursuppgifterFromCanvas(String kurskod)
	{
		LOG.info("getKursuppgifterFromCanvas() called");
		return db.getKursuppgifterFromCanvas(kurskod);
	}


	@GET
	@Path("studenter")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Canvas_Student> getCanvasStudents()
	{
		return db.getCanvasStudents();
	}


}