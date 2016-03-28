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
import rs.Seminarski.authentication.SecureAdmin;
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
	//@SecureAdmin
	@Path ("/add")
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void addItem (Proizvod p) {
		dao.insertItem(p);
	}
	
	@GET
	//@SecureAdmin
	@Path ("/return/distinct")
	@Produces (MediaType.APPLICATION_JSON)
	public ArrayList<Proizvod> selectDistinctType () {	
		return dao.returnDistinctType();
	}
	
	@GET
	@SecureAdmin
	@Path("/getitem/byadmin/{id}")
	public Proizvod getByIdAdmin (@PathParam("id") int id) {
		return  dao.getById(id);
	} 
	
	@PUT
	//@SecureAdmin
	@Path("/update/item/all")
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateItemWithId ( Proizvod pro) {
		dao.updateItem(pro);
	} 
	
	@DELETE
	//@SecureAdmin
	@Path("/{delete}")
	public void deleteItem (@PathParam ("delete")int id) {
		dao.deleteItem(id);;
	}
	
	
}
