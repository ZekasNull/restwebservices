package data_objects.canvas;

public class Canvas_Student {
	private String användare;
	private String namn;

	//constructor
	public Canvas_Student()
	{
	}

	//region Getters and Setters
	public String getAnvändare()
	{
		return användare;
	}

	public void setAnvändare(String användare)
	{
		this.användare = användare;
	}

	public String getNamn()
	{
		return namn;
	}

	public void setNamn(String namn)
	{
		this.namn = namn;
	}
	//endregion
}
