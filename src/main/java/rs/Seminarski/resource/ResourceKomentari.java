package rs.Seminarski.resource;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import io.jsonwebtoken.Jwts;
import rs.Seminarski.dao.KomentariDAO;
import rs.Seminarski.dao.KorisnikKomentariDAO;
import rs.Seminarski.model.Komentari;
import rs.Seminarski.model.KorisnikKomentari;
import rs.Seminarski.service.ServicePagination;

@Path("/comments")
public class ResourceKomentari {
	
	KomentariDAO kodao = new KomentariDAO ();
	KorisnikKomentariDAO kkdao = new KorisnikKomentariDAO();
	
	ServicePagination pagination = new ServicePagination ();
	
	@POST
	@Path ("/add")
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void insertComments(Komentari kom, @Context HttpServletRequest request) {
		
		String tokenHeaderString = request.getHeader("Authorization");
		//String tokenString = request.getHeader("token");
		String tokenString = tokenHeaderString.substring("Bearer ".length()).trim(); 
		int idKorisnika = Integer.parseInt(Jwts.parser().setSigningKey("kljuc").parseClaimsJws(tokenString).getBody().getSubject());
		
		System.out.println("idKorisnika: " +idKorisnika);
		
		 kodao.insertComments(idKorisnika, kom.getId_proizvoda(), kom.getKomentar());
		
	}
	
	@GET
	@Path ("/getcomments/{id_proizvoda}")
	@Produces (MediaType.APPLICATION_JSON)
	public ArrayList<KorisnikKomentari> getComments(@PathParam("id_proizvoda") int id_proizvoda) {
		return kkdao.getComments(id_proizvoda);
	}
	
	// admin resource comments
	
	@GET
	@Path ("/admindistinct")
	@Produces (MediaType.APPLICATION_JSON)
	public ArrayList<KorisnikKomentari> getAdminDistinctComments() {
		return kkdao.getAdminDistinctComments();
	}
	
	@DELETE
	@Path ("/{delete}")
	public void deleteComments (@PathParam("delete") int id) {
		kkdao.deleteComments(id);
	}
	
	@DELETE
	@Path ("/delete/one/{Idcomment}")
	public void deleteOneComment (@PathParam("Idcomment") int id) {
		kkdao.deleteOneComment(id);
	}
	
	//admin get 10 comments
	
	@GET
	@Path ("/get/only/ten/for/product/{id}/{page_no}")
	@Produces (MediaType.APPLICATION_JSON)
	public ArrayList<KorisnikKomentari> getOnlyTenComments(@PathParam("id") int id_proizvoda, @PathParam("page_no") int page_no) {
		return pagination.getServiceTenComments(id_proizvoda, page_no);
	} 
	
	@GET
	@Path ("/get/count/{id}")
	//@Produces (MediaType.APPLICATION_JSON)
	public int getCountComments(@PathParam("id") int id) {
		return kkdao.getCountComments(id);
		
	}
	

}
