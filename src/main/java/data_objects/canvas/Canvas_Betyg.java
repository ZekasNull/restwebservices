package data_objects.canvas;

public class Canvas_Betyg {
	private int betygID;
	private Integer uppgiftNr;
	private String användare;
	private String betyg;

	//constructors
	public Canvas_Betyg()
	{

	}

	//region Getters and Setters
	public int getBetygID()
	{
		return betygID;
	}

	public void setBetygID(int betygID)
	{
		this.betygID = betygID;
	}

	public Integer getUppgiftNr()
	{
		return uppgiftNr;
	}

	public void setUppgiftNr(Integer uppgiftNr)
	{
		this.uppgiftNr = uppgiftNr;
	}

	public String getAnvändare()
	{
		return användare;
	}

	public void setAnvändare(String användare)
	{
		this.användare = användare;
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
