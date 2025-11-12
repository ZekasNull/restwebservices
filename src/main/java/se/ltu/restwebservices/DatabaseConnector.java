package se.ltu.restwebservices;

import data_objects.Ladok_Resultat;
import data_objects.Studentits_Student;
import data_objects.canvas.Canvas_StudentBetygDTO;
import data_objects.epok.Epok_Modul;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Hanterar anslutning och anrop till databasen.
 */
public class DatabaseConnector {
	//Anslutningsdetaljer - måste ändras om något annat användas lokalt.
	private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/restwebservices";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	/**
	 * Öppnar en anslutning till databasen.
	 *
	 * @return Anslutningen som ett Connection-objekt
	 * @throws SQLException om url eller credentials inte matchar
	 */
	public static Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
	}

	//region Database calls

	//Förmodligen kan alla anrop läggas här då de inte är så många till antalet.
	// Bör omfaktoreras om komplexitet växer, förmodligen inte SOLID att ha dem här, men enkelt atm

	/**
	 * Hämtar alla moduler för en given kurskod.
	 *
	 * @param kurskod Kurskoden för den kurs vars moduler söks
	 * @return en ArrayList av kursens moduler (kan vara tom)
	 */
	public ArrayList<Epok_Modul> getModulesFromEpok(String kurskod)
	{
		//variabler
		String query = "SELECT * FROM epok_modul WHERE kurskod = ?";
		ArrayList<Epok_Modul> modules = new ArrayList<>();

		//db call
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query))
		{
			stmt.setString(1, kurskod); //set user input
			ResultSet rs = stmt.executeQuery();

			while (rs.next())
			{
				modules.add(new Epok_Modul
						(
								kurskod,
								rs.getInt("Modulkod"),
								rs.getString("Modulnamn"),
								rs.getString("Betygsskala"))
				);
			}

		} catch (SQLException e)
		{
			logDatabaseError(e);
			//throw new RuntimeException(e); //kasta ny exception för att hantera på annat ställe?
		}

		return modules;
	}

	/**
	 * Anropar databasen för att hämta ett studentobjekt.
	 *
	 * @param användarnamn för det personnummer som söks
	 * @return ett studentits_student dataobjekt (skapas alltid, kan vara tom om ingen hittades)
	 */
	public Studentits_Student getStudentFromStudentits(String användarnamn)
	{
		String query = "SELECT * FROM studentits_student WHERE Användare = ?";
		Studentits_Student student = new Studentits_Student();
		//FIXME ska tomma objekt tillåtas som retur eller ska det kasta ett exception?

		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query))
		{
			stmt.setString(1, användarnamn);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
			{ //har antingen 0 eller 1 resultat
				student.setAnvändare(rs.getString("Användare"));
				student.setPersonnummer(rs.getString("Personnummer"));
			}


		} catch (SQLException e)
		{
			logDatabaseError(e);
		}
		return student;
	}

	//TODO ladok calls (insert studieresultat + some reads?)

	/**
	 * Försöker lägga till ett Ladok_Resultat-objekt i databasen.
	 *
	 * @param resultat det som ska läggas till
	 * @return True om anropet lyckas, annars false
	 */
	public boolean InsertNewLadokResult(Ladok_Resultat resultat)
	{
		String query = "INSERT INTO ladok_resultat (Personnummer, Kurskod, Modulkod, Datum, Betyg) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query))
		{
			//prepare insert
			stmt.setString(1, resultat.getPersonnummer());
			stmt.setString(2, resultat.getKurskod());
			stmt.setInt(3, resultat.getModulkod());
			stmt.setDate(4, resultat.getDatum());
			stmt.setString(5, resultat.getBetyg());
			//execute
			stmt.executeUpdate();
			return true; //only reachable for success in theory
		} catch (SQLException e)
		{
			logDatabaseError(e);
		}
		return false;
	}


	//hämtningar från canvas börjar med kurs och modul (=kursuppgift)...

	/**
	 * Hämtar alla betyg för en given kurskod och uppgift.
	 *
	 * @param kurskod     Kurskoden
	 * @param kursuppgift Kursuppgiftens nummer
	 * @return En lista av alla betyg formaterade som Data Transfer Objects (DTO).
	 */
	public List<Canvas_StudentBetygDTO> getCanvasGrades(String kurskod, int kursuppgift)
	{
		List<Canvas_StudentBetygDTO> resultList = new ArrayList<>();
		String query = """
				SELECT 
				    s.Användare,
				    s.Namn,
				    k.Kurskod,
				    k.Kursnamn,
				    ku.Uppgift_nr,
				    ku.Uppgiftsnamn,
				    b.Betyg
				FROM canvas_betyg b
				INNER JOIN canvas_student s ON b.Användare = s.Användare
				INNER JOIN canvas_kursuppgift ku ON b.Uppgift_nr = ku.Uppgift_nr
				INNER JOIN canvas_kurs k ON ku.Kurskod = k.Kurskod
				WHERE k.Kurskod = ? AND ku.Uppgift_nr = ?
				ORDER BY s.Namn
				""";

		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query))
		{
			stmt.setString(1, kurskod);
			stmt.setInt(2, kursuppgift);
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
			{
				resultList.add(new Canvas_StudentBetygDTO(
						rs.getString("Användare"),
						rs.getString("Namn"),
						rs.getString("Kurskod"),
						rs.getString("Kursnamn"),
						rs.getInt("Uppgift_nr"),
						rs.getString("Uppgiftsnamn"),
						rs.getString("Betyg")));
			}
		} catch (SQLException e)
		{
			logDatabaseError(e);
		}
		return resultList;
	}

	//endregion

	//region helper methods
	private void logDatabaseError(SQLException e)
	{
		System.out.println("[DatabaseConnector] Database returned error code \"" + e.getErrorCode() + "\" and message \"" + e.getMessage() + "\"");
	}
	//endregion

	//quick test to verify connection works - FIXME remove later
	public static void main(String[] args)
	{
		DatabaseConnector db = new DatabaseConnector();

		/*
		Ladok_Resultat resultat = new Ladok_Resultat();
		// keys
		resultat.setKurskod("D0666N");
		resultat.setModulkod(1);
		resultat.setPersonnummer("136201012468");
		//details
		resultat.setDatum(new Date(System.currentTimeMillis()));
		resultat.setBetyg("G");

		//attempt insert
		System.out.println(db.InsertNewLadokResult(resultat));

		 */

		List<Canvas_StudentBetygDTO> testlist = db.getCanvasGrades("D0666N", 1);

		for (Canvas_StudentBetygDTO dto : testlist)
		{
			System.out.println(dto);
		}

	}

}
