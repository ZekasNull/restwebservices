/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_objects.canvas;

/**
 *
 * @author vendela
 */
public class Canvas_Kurs {

    private String kurskod;
	private String kursnamn;

	//constructors
	public Canvas_Kurs()
	{
	}

	//region Getters and Setters
	public String getKurskod()
	{
		return kurskod;
	}

	public void setKurskod(String kurskod)
	{
		this.kurskod = kurskod;
	}

	public String getKursnamn()
	{
		return kursnamn;
	}

	public void setKursnamn(String kursnamn)
	{
		this.kursnamn = kursnamn;
	}
	//endregion
}
