package rs.Seminarski.service;

import rs.Seminarski.dao.KorisnikDAO;

public class ServiceUserRestPassword {
	
KorisnikDAO kdao = new KorisnikDAO ();
	
	public String existEmailForChangePassword(String email) {
		if (kdao.existUserWithEmailForChangePassword(email) != null) {
			return email;	
		} else {
			return null; 
		}
		}

}
