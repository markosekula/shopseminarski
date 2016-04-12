package rs.Seminarski.model;

import java.math.BigDecimal;

public class AdminKorpa {
	
	private String email;
	private String vrsta;
	private String proizvodjac;
	private String model;
	private BigDecimal cena;
	private int kolicina;
	private BigDecimal ukupna_cena;
		
	public AdminKorpa() {
		super();
		
	}
	
	public AdminKorpa(String email, String vrsta, String proizvodjac, String model, BigDecimal cena, int kolicina,
			BigDecimal ukupna_cena) {
		super();
		this.email = email;
		this.vrsta = vrsta;
		this.proizvodjac = proizvodjac;
		this.model = model;
		this.cena = cena;
		this.kolicina = kolicina;
		this.ukupna_cena = ukupna_cena;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVrsta() {
		return vrsta;
	}

	public void setVrsta(String vrsta) {
		this.vrsta = vrsta;
	}

	public String getProizvodjac() {
		return proizvodjac;
	}

	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public BigDecimal getCena() {
		return cena;
	}

	public void setCena(BigDecimal cena) {
		this.cena = cena;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public BigDecimal getUkupna_cena() {
		return ukupna_cena;
	}

	public void setUkupna_cena(BigDecimal ukupna_cena) {
		this.ukupna_cena = ukupna_cena;
	}

}
