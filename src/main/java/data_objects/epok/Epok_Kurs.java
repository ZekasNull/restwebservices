package data_objects.epok;

public class Epok_Kurs {
	private String kurskod;

	//constructor

	public Epok_Kurs(String kurskod)
	{
		if (kurskod.isEmpty())
		{
			throw new IllegalArgumentException("field 'kurskod' cannot be blank");
		}
		this.kurskod = kurskod;
	}

	//region Getters and setters
	public String getKurskod()
	{
		return kurskod;
	}

	public void setKurskod(String kurskod)
	{
		this.kurskod = kurskod;
	}
	//endregion
}
