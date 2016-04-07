package rs.Seminarski.resource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import io.jsonwebtoken.Jwts;
import rs.Seminarski.authentication.Secure;
import rs.Seminarski.dao.KupljeniProizvodiDAO;
import rs.Seminarski.dao.KupovinaDAO;
import rs.Seminarski.model.Kupovina;
import rs.Seminarski.model.Proizvod;

@Path ("/shopping")
public class ResourceKupovina {
	
	KupovinaDAO daok = new KupovinaDAO ();
	KupljeniProizvodiDAO kdao = new KupljeniProizvodiDAO();  
	
	@POST
	@Path ("/add")
	//@Secure
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void addCart (Kupovina k, @Context HttpServletRequest request) {
		
		String tokenHeaderString = request.getHeader("Authorization");
		String tokenString = tokenHeaderString.substring("Bearer".length()).trim(); 
		int idKorisnika = Integer.parseInt(Jwts.parser().setSigningKey("kljuc").parseClaimsJws(tokenString).getBody().getSubject());
		
		System.out.println("idKorisnika: " +idKorisnika);
		
		int idKupovine = daok.insertKupovina(k.getUkupna_cena(), idKorisnika);
		
		System.out.println("idKupovine: " +idKupovine);
		
		if(idKupovine != -1){
		for(Proizvod proizvod : k.getCart()){
			
			System.out.println(k.getCart());
			System.out.println(proizvod.getId());
			kdao.insertPurchasedItems(idKupovine, proizvod.getId());
		}
		}
		
	} 
	
}

