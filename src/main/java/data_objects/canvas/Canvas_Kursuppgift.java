package data_objects.canvas;

public class Canvas_Kursuppgift {
	private int uppgiftNr; //id, onödig?
	private String kurskod;
	private String uppgiftsnamn;

	//constructor
	public Canvas_Kursuppgift()
	{
	}

    public Canvas_Kursuppgift(int uppgiftNr, String kurskod, String uppgiftsnamn)
    {
        this.uppgiftNr = uppgiftNr;
        this.kurskod = kurskod;
        this.uppgiftsnamn = uppgiftsnamn;
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
