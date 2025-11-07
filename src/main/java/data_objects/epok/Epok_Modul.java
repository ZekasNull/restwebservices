package data_objects.epok;

public class Epok_Modul {
	private String kurskod;
	private int modulkod;
	private String modulnamn;
	private String betygsskala; // ENUM values "U", "G", "VG" defined by database

	//region Constructors

	/**
	 * Skapar en Epok_Modul utan att sätta några klassvariabler.
	 */
	public Epok_Modul()
	{
	}

	/**
	 * Skapar en Epok_Modul som omedelbart fyller sina klassvariabler.
	 *
	 * @param kurskod
	 * @param modulkod
	 * @param modulnamn
	 * @param betygsskala
	 */
	public Epok_Modul(String kurskod, int modulkod, String modulnamn, String betygsskala)
	{
		this.kurskod = kurskod;
		this.modulkod = modulkod;
		this.modulnamn = modulnamn;
		this.betygsskala = betygsskala;
	}
	//endregion

	//region Getters and Setters
	public String getKurskod()
	{
		return kurskod;
	}

	public void setKurskod(String kurskod)
	{
		this.kurskod = kurskod;
	}

	public int getModulkod()
	{
		return modulkod;
	}

	public void setModulkod(int modulkod)
	{
		this.modulkod = modulkod;
	}

	public String getModulnamn()
	{
		return modulnamn;
	}

	public void setModulnamn(String modulnamn)
	{
		this.modulnamn = modulnamn;
	}

	public String getBetygsskala()
	{
		return betygsskala;
	}

	public void setBetygsskala(String betygsskala)
	{
		this.betygsskala = betygsskala;
	}
	//endregion
}
