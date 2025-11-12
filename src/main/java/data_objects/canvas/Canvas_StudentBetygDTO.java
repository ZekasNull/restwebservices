package data_objects.canvas;

public class Canvas_StudentBetygDTO {
	private String användare;
	private String studentNamn;
	private String kurskod;
	private String kursnamn;
	private Integer uppgiftNr;
	private String uppgiftsnamn;
	private String betyg;

	public Canvas_StudentBetygDTO(String användare, String studentNamn,
								  String kurskod, String kursnamn,
								  Integer uppgiftNr, String uppgiftsnamn,
								  String betyg)
	{
		this.användare = användare;
		this.studentNamn = studentNamn;
		this.kurskod = kurskod;
		this.kursnamn = kursnamn;
		this.uppgiftNr = uppgiftNr;
		this.uppgiftsnamn = uppgiftsnamn;
		this.betyg = betyg;
	}

	//region  Getters and setters
	public String getAnvändare()
	{
		return användare;
	}

	public void setAnvändare(String användare)
	{
		this.användare = användare;
	}

	public String getStudentNamn()
	{
		return studentNamn;
	}

	public void setStudentNamn(String studentNamn)
	{
		this.studentNamn = studentNamn;
	}

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

	public Integer getUppgiftNr()
	{
		return uppgiftNr;
	}

	public void setUppgiftNr(Integer uppgiftNr)
	{
		this.uppgiftNr = uppgiftNr;
	}

	public String getUppgiftsnamn()
	{
		return uppgiftsnamn;
	}

	public void setUppgiftsnamn(String uppgiftsnamn)
	{
		this.uppgiftsnamn = uppgiftsnamn;
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

	@Override
	public String toString()
	{
		return "StudentGradeDTO{" +
				"anvandare='" + användare + '\'' +
				", studentNamn='" + studentNamn + '\'' +
				", kurskod='" + kurskod + '\'' +
				", kursnamn='" + kursnamn + '\'' +
				", uppgiftNr=" + uppgiftNr +
				", uppgiftsnamn='" + uppgiftsnamn + '\'' +
				", betyg='" + betyg + '\'' +
				'}';
	}
}