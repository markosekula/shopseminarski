package rs.Seminarski.resource;

import java.util.ArrayList;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import rs.Seminarski.dao.AdminKorpaDAO;
import rs.Seminarski.model.AdminKorpa;

@Path ("/admincart")
public class ResourceAdminKorpa {
	
	AdminKorpaDAO akdao = new AdminKorpaDAO();
	
	@GET
	@Path("/{idkupovine}")
	public ArrayList<AdminKorpa>  getAllDataFromCart(@PathParam("idkupovine") int id_kupovine) {
		return  akdao.getAllData(id_kupovine );	
	} 
	
	@DELETE
	@Path("/delete/{delete}")
	public void deletePurchase (@PathParam ("delete")int id) {
		akdao.deletePurchase(id);
	}

}
