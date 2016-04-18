package rs.Seminarski.model;

import java.sql.Timestamp;

public class Komentari {
	
	private int id;
	private int id_korisnika;
	private int id_proizvoda;
	private String komentar;
	private Timestamp vreme;
	
	public Komentari() {
		super();
	}

	public Komentari(int id_korisnika, int id_proizvoda, String komentar, Timestamp vreme) {
		super();
		this.id_korisnika = id_korisnika;
		this.id_proizvoda = id_proizvoda;
		this.komentar = komentar;
		this.vreme = vreme;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_korisnika() {
		return id_korisnika;
	}

	public void setId_korisnika(int id_korisnika) {
		this.id_korisnika = id_korisnika;
	}

	public int getId_proizvoda() {
		return id_proizvoda;
	}

	public void setId_proizvoda(int id_proizvoda) {
		this.id_proizvoda = id_proizvoda;
	}

	public String getKomentar() {
		return komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}

	public Timestamp getVreme() {
		return vreme;
	}

	public void setVreme(Timestamp vreme) {
		this.vreme = vreme;
	}

}
