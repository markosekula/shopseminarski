package rs.Seminarski.resource;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import rs.Seminarski.dao.KupljeniProizvodiDAO;
import rs.Seminarski.model.KupljeniProizvodi;

@Path("/purchased")
public class ResourceKupljeniProizvodi {
	
	KupljeniProizvodiDAO kdao = new KupljeniProizvodiDAO();
	
	

}
