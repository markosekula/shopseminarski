package rs.Seminarski.model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Kupovina {

	private int id;
	private ArrayList<Proizvod> cart;
	private BigDecimal ukupna_cena;
	
	public Kupovina() {
		super();
	}

	public Kupovina(ArrayList<Proizvod> cart, BigDecimal ukupna_cena ) {
		super();
		this.cart = cart;
		this.ukupna_cena = ukupna_cena;
	}

	public ArrayList<Proizvod> getCart() {
		return cart;
	}

	public void setCart(ArrayList<Proizvod> cart) {
		this.cart = cart;
	}

	public BigDecimal getUkupna_cena() {
		return ukupna_cena;
	}

	public void setUkupna_cena(BigDecimal ukupna_cena) {
		this.ukupna_cena = ukupna_cena;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
