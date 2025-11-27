package webservices;

import data_objects.canvas.Canvas_StudentBetygDTO;
import database.DatabaseConnector;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("canvas")
public class CanvasResource {

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
}