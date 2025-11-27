
package webservices;

import data_objects.Studentits_Student;
import database.DatabaseConnector;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("studentIts")
public class StudentItsResource {

	private final static DatabaseConnector db = new DatabaseConnector();

	// /resources/studentIts/student?Användare=xxxx
	@GET
	@Path("student")
	@Produces(MediaType.APPLICATION_JSON)
	public static Studentits_Student getStudent(@QueryParam("Användare") String användarnamn)
	{

		if (användarnamn == null || användarnamn.isEmpty())
		{
			return new Studentits_Student();
		}

		return db.getStudentFromStudentits(användarnamn);
	}
}