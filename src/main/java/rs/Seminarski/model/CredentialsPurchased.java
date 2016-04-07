package rs.Seminarski.model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CredentialsPurchased {
	
	private ArrayList<Object> cart;
	private BigDecimal ukupna_cena;
	private int korinsik_id;
	
	public ArrayList<Object> getCart() {
		return cart;
	}
	public void setCart(ArrayList<Object> cart) {
		this.cart = cart;
	}
	public BigDecimal getUkupna_cena() {
		return ukupna_cena;
	}
	public void setUkupna_cena(BigDecimal ukupna_cena) {
		this.ukupna_cena = ukupna_cena;
	}
	public int getKorinsik_id() {
		return korinsik_id;
	}
	public void setKorinsik_id(int korinsik_id) {
		this.korinsik_id = korinsik_id;
	}
	
	
	
	

}
