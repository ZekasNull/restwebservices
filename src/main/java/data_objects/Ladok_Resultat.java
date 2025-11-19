/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_objects;

import java.sql.Date;

/**
 *
 * @author vendela
 */
public class Ladok_Resultat {
	private String personnummer;
	private String kurskod;
	private int modulkod;
	private Date datum;
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

	public Date getDatum()
	{
		return datum;
	}

	public void setDatum(Date datum)
	{
		this.datum = datum;
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
