package rs.Seminarski.service;

import rs.Seminarski.dao.KorisnikDAO;
import rs.Seminarski.model.Korisnik;


public class ServiceKorisnik {
	
	KorisnikDAO kdao = new KorisnikDAO ();
	
	public void addKorisnik (Korisnik k) {
		kdao.insertKlient(k);
	}
	

	public boolean existEmail(String email) {
		if (kdao.existUserWithEmail(email) ) {
			return false;	
		} else {
			return true; 
		}
		}
	}
	


