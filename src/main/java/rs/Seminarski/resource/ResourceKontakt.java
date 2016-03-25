package rs.Seminarski.resource;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import rs.Seminarski.dao.KontaktDAO;
import rs.Seminarski.model.Kontakt;


@Path("/kontakt")
public class ResourceKontakt {
	
	KontaktDAO daok = new KontaktDAO ();
	
	@GET
	@Path ("/all")
	@Produces (MediaType.APPLICATION_JSON)
	public ArrayList<Kontakt> selectAllMessage () {	
		return daok.selectMessage();
	}

}
