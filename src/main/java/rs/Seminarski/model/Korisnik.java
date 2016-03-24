package rs.Seminarski.model;

public class Korisnik {

	private int id;
	private String ime,prezime,email,pass;
	private byte admin;

	public Korisnik() {
		super();

	}

	public Korisnik(String ime, String prezime, String email, String pass, byte admin) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.pass = pass;
		this.admin = admin;
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

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public byte getAdmin() {
		return admin;
	}

	public void setAdmin(byte admin) {
		this.admin = admin;
	}
	
}
