package rs.Seminarski.model;

import java.sql.Timestamp;

public class KorisnikKomentari {

	private int id;
	private String ime;
	private String prezime;
	private String komentar;
	private Timestamp vreme;
	
	private int id_proizvoda;
	private String vrsta;
	private String proizvodjac;
	private String model;
	
	private int count;
	
	public KorisnikKomentari(int id, String ime, String prezime, String komentar, Timestamp vreme, int id_proizvoda,
			String vrsta, String proizvodjac, String model, int count) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.komentar = komentar;
		this.vreme = vreme;
		this.id_proizvoda = id_proizvoda;
		this.vrsta = vrsta;
		this.proizvodjac = proizvodjac;
		this.model = model;
		this.count = count;
	}
	
	public KorisnikKomentari() {
		super();
			}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public String getProizvodjac() {
		return proizvodjac;
	}

	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
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


	public int getId_proizvoda() {
		return id_proizvoda;
	}

	public void setId_proizvoda(int id_proizvoda) {
		this.id_proizvoda = id_proizvoda;
	}

	public String getVrsta() {
		return vrsta;
	}

	public void setVrsta(String vrsta) {
		this.vrsta = vrsta;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	
	
}
