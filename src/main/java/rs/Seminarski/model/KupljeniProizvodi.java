package rs.Seminarski.model;

public class KupljeniProizvodi {
	
	private int id;
	private int id_kupovine;
	private int id_proizvoda;
	
	public KupljeniProizvodi() {
		super();
		
	}

	public KupljeniProizvodi(int id_kupovine, int id_proizvoda) {
		super();
		this.id_kupovine = id_kupovine;
		this.id_proizvoda = id_proizvoda;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_kupovine() {
		return id_kupovine;
	}

	public void setId_kupovine(int id_kupovine) {
		this.id_kupovine = id_kupovine;
	}

	public int getId_proizvoda() {
		return id_proizvoda;
	}

	public void setId_proizvoda(int id_proizvoda) {
		this.id_proizvoda = id_proizvoda;
	}

}
