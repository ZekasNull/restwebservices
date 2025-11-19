/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_objects;

/**
 *
 * @author vendela
 */
public class Studentits_Student {
    	private String personnummer;
	private String användare;

	//constructor
	public Studentits_Student()
	{

	}

	//region Getters and Setters
	public String getPersonnummer()
	{
		return personnummer;
	}

	public void setPersonnummer(String personnummer)
	{
		this.personnummer = personnummer;
	}

	public String getAnvändare()
	{
		return användare;
	}

	public void setAnvändare(String användare)
	{
		this.användare = användare;
	}
	//endregion
        
            @Override
    public String toString() {
        return "Student{id=" + personnummer + ", name='" + användare + "'}";
    }
}
    

