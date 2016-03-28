package rs.Seminarski.resource;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import rs.Seminarski.dao.KontaktDAO;
import rs.Seminarski.model.Kontakt;

@Path("/contact")
public class ResourceKontakt {
	
	KontaktDAO daok = new KontaktDAO ();
	
	@GET
	@Path ("/getall")
	@Produces (MediaType.APPLICATION_JSON)
	public ArrayList<Kontakt> selectAllMessage () {	
		return daok.selectMessage();
	}

	@POST
	@Path ("/add")
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void addKontakt (Kontakt k) {
		daok.insertKontakt(k);;
	}
	
	@DELETE
	@Path ("/{delete}")
	public void deleteKontakt (@PathParam("delete") int id) {
		daok.deleteKontakt(id);
	}
}
