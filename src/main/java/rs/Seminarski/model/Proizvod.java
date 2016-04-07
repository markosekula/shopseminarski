package rs.Seminarski.model;

import java.math.BigDecimal;

public class Proizvod {
	
	private int id;
	
	private String vrsta,proizvodjac,model,garancija,slika;
	private String tip,kapacitet,socket,takt;
	
	private BigDecimal cena,dijagonala;
	private byte akcija;
	
	public Proizvod() {
		super();
	}

	public Proizvod(String vrsta, String proizvodjac, String model, String garancija, String slika, String tip,
			String kapacitet, String socket, String takt, BigDecimal cena, BigDecimal dijagonala, byte akcija) {
		super();
		this.vrsta = vrsta;
		this.proizvodjac = proizvodjac;
		this.model = model;
		this.garancija = garancija;
		this.slika = slika;
		this.tip = tip;
		this.kapacitet = kapacitet;
		this.socket = socket;
		this.takt = takt;
		this.cena = cena;
		this.dijagonala = dijagonala;
		this.akcija = akcija;
		
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getGarancija() {
		return garancija;
	}

	public void setGarancija(String garancija) {
		this.garancija = garancija;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(String kapacitet) {
		this.kapacitet = kapacitet;
	}

	public String getSocket() {
		return socket;
	}

	public void setSocket(String socket) {
		this.socket = socket;
	}

	public String getTakt() {
		return takt;
	}

	public void setTakt(String takt) {
		this.takt = takt;
	}

	public BigDecimal getCena() {
		return cena;
	}

	public void setCena(BigDecimal cena) {
		this.cena = cena;
	}

	public BigDecimal getDijagonala() {
		return dijagonala;
	}

	public void setDijagonala(BigDecimal dijagonala) {
		this.dijagonala = dijagonala;
	}

	public byte getAkcija() {
		return akcija;
	}

	public void setAkcija(byte akcija) {
		this.akcija = akcija;
	}
	
	
	
}
