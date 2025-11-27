package webservices;

import data_objects.Ladok_Resultat;
import database.DatabaseConnector;
import database.DatabaseResponse;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.logging.Level;
import java.util.logging.Logger;

@Path("ladok")
public class LadokResource {
	// man skickar i form av denna mall via java
	// {"personnummer": "293103011239", "kurskod": "M1111A", "modulkod": 5, "datum": "2025-11-15", "betyg": "VG"}

	private final static DatabaseConnector db = new DatabaseConnector();

	@POST
	@Path("regResultat")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public static Response registerResult(Ladok_Resultat resultat)
	{

		boolean success = db.InsertNewLadokResult(resultat);

		if (success)
		{
			return Response.ok("registrerad").build();
		} else
		{
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("hinder")
					.build();
		}
	}
}