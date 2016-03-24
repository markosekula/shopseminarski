package rs.Seminarski.resource;
 
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import rs.Seminarski.authentication.Secure;
import rs.Seminarski.dao.ProizvodiDAO;
import rs.Seminarski.model.Proizvod;
import rs.Seminarski.service.ServiceProizvodi;

@Path ("/items")
public class ResourceProizvodi {
	
	ProizvodiDAO dao = new ProizvodiDAO();
	ServiceProizvodi spr=new ServiceProizvodi();
	
	@GET
	//@Secure
	@Path("/getitem/{type}")
	public ArrayList<Proizvod>  getChooseItem (@PathParam("type") String vrsta) {
		return  dao.getChooseItem(vrsta);	
	} 

	@GET
	//@Secure
	@Path("/getitem/by/{id}")
	public Proizvod getById (@PathParam("id") int id) {
		return  dao.getById(id);
	} 
	
	@GET
	////@Secure
	@Path ("/random/all")
	@Produces (MediaType.APPLICATION_JSON)
	public ArrayList<Proizvod> selectRandom () {	
		return dao.selectRandom();
	}
	
	@GET
	//@Secure
	@Path ("/random/on/action")
	@Produces (MediaType.APPLICATION_JSON)
	public ArrayList<Proizvod> selectRandomOnAction () {	
		return dao.selectRandomOnAction();
	}
	
	@POST
	@Path ("/add")
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void addTastatura (Proizvod p) {
		dao.insertItem(p);
	}
	
	@GET
	@Path ("/return/distinct")
	@Produces (MediaType.APPLICATION_JSON)
	public ArrayList<Proizvod> selectDistinctType () {	
		return dao.returnDistinctType();
	}
	
	@PUT
	@Path("/update/item/all")
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateItemWithId ( Proizvod pro) {
		dao.updateItem(pro);
	} 
	
	/*
	@PUT
	@Path("/update/item/with/{id}")
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateItemWithId (@PathParam("id") int id, Proizvod pro) {
		dao.updateItem(pro);
	} */
	
	/*
	
	@DELETE
	@Path("/{delete}")
	public void deleteTastatura (@PathParam ("delete")String sifra) {
		dao.deleteTastatura(sifra);
	}
	
	*/
}
