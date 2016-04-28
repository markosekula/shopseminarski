package rs.Seminarski.resetUserPassword;

import rs.Seminarski.dao.KorisnikDAO;

public class UserServiceRest {
	
	public UserServiceRest() {

	}
	KorisnikDAO kdao = new KorisnikDAO ();
	
	public boolean existEmailForChangePassword(String email) {
		if (kdao.existUserWithEmail(email) ) {
			return true;	
		} else {
			return false; 
		}
		}

}
