package rs.Seminarski.service;

import java.util.ArrayList;

import rs.Seminarski.dao.KorisnikKomentariDAO;
import rs.Seminarski.model.KorisnikKomentari;

public class ServicePagination {
	
	KorisnikKomentariDAO kkdao = new KorisnikKomentariDAO();

	int commentsPerPage = 10;
	
	public ArrayList<KorisnikKomentari> getServiceTenComments( int id_proizvoda, int page) {
		return kkdao.getTenComments(id_proizvoda, (page-1)*commentsPerPage);
	}
	
	

}
