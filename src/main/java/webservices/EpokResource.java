package webservices;

import data_objects.epok.Epok_Modul;
import database.DatabaseConnector;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Path("epok")
public class EpokResource {

	private final static DatabaseConnector db = new DatabaseConnector();

	// skriv typ /resources/epok/moduler?kurskod=1
	@GET
	@Path("moduler")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Epok_Modul> getModul(@QueryParam("kurskod") String kurskod)
	{

		if (kurskod == null || kurskod.isEmpty())
		{
			return new ArrayList<>();
		}

		return db.getModulesFromEpok(kurskod);
	}
}
