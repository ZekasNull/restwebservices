package data_objects;

import jakarta.json.bind.annotation.JsonbDateFormat;

import java.sql.Date;

public class Ladok_Resultat {
	private String personnummer;
	private String kurskod;
	private int modulkod;
	@JsonbDateFormat("yyyy-MM-dd")
	private Date sqlDatum;
	private String date;
	private String betyg;


	//region Getters and Setters
	public String getPersonnummer()
	{
		return personnummer;
	}

	public void setPersonnummer(String personnummer)
	{
		this.personnummer = personnummer;
	}

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

	public String getDate()
	{
		return this.date;
	}

	public void setSqlDatum(Date sqlDatum)
	{
		this.sqlDatum = sqlDatum;
		this.date = sqlDatum.toString();
	}

	public String getBetyg()
	{
		return betyg;
	}

	public void setBetyg(String betyg)
	{
		this.betyg = betyg;
	}
	//endregion
}
