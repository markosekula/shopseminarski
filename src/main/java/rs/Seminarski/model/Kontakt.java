package rs.Seminarski.model;

public class Kontakt {

	private int id;
	private String ime;
	private String email;
	private String poruka;
	
	public Kontakt() {
		super();
	}

	public Kontakt( String ime, String email, String poruka) {
		super();
		this.ime = ime;
		this.email = email;
		this.poruka = poruka;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPoruka() {
		return poruka;
	}

	public void setPoruka(String poruka) {
		this.poruka = poruka;
	}
	
	
	
	
}
