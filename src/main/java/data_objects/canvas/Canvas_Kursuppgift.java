/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_objects.canvas;

/**
 *
 * @author vendela
 */
public class Canvas_Kursuppgift {
    private int uppgiftNr; //id, onödig?
	private String kurskod;
	private String uppgiftsnamn;

	//constructor
	public Canvas_Kursuppgift()
	{
	}

	//region Getters and Setters
	public int getUppgiftNr()
	{
		return uppgiftNr;
	}

	public void setUppgiftNr(int uppgiftNr)
	{
		this.uppgiftNr = uppgiftNr;
	}

	public String getKurskod()
	{
		return kurskod;
	}

	public void setKurskod(String kurskod)
	{
		this.kurskod = kurskod;
	}

	public String getUppgiftsnamn()
	{
		return uppgiftsnamn;
	}

	public void setUppgiftsnamn(String uppgiftsnamn)
	{
		this.uppgiftsnamn = uppgiftsnamn;
	}
	//endregion
}
