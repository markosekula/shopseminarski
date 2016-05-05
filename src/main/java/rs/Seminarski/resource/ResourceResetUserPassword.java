package rs.Seminarski.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import rs.Seminarski.dao.KorisnikDAO;
import rs.Seminarski.model.Korisnik;
import rs.Seminarski.resetUserPassword.SendEmail;
import rs.Seminarski.service.ServiceUserRestPassword;

@Path("/reset-password")
public class ResourceResetUserPassword {

	SendEmail sendemail = new SendEmail ();
	ServiceUserRestPassword usr = new ServiceUserRestPassword();
	KorisnikDAO daok = new KorisnikDAO ();
		
		@GET
		@Path("/{email}")
		@Produces(MediaType.TEXT_PLAIN)
		public String resetUserPassword (@PathParam("email") String email) {
			if (usr.existEmailForChangePassword(email) != null) {
				SendEmail.sendEmail(email);
				return "Success send email!"; 
				} 
			return "Please, register first!";
		} 
		
		@PUT
		@Path("/update")
		@Produces (MediaType.TEXT_PLAIN)
		@Consumes(MediaType.APPLICATION_JSON)
		public void changeUserPassword( Korisnik k) {
			daok.changePassword(k);	
		} 
		
}
