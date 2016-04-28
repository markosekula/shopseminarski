package rs.Seminarski.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import rs.Seminarski.resetUserPassword.SendEmail;
import rs.Seminarski.resetUserPassword.UserServiceRest;

@Path("/reset")
public class ResourceResetPassword {
	
	UserServiceRest usr = new UserServiceRest();
	//SendEmail semail = new SendEmail();
	
	@GET
	@Path("/password/{email}")
	@Produces (MediaType.APPLICATION_JSON)
	public void resetPassword(@PathParam("email") String email) { 
		//if (usr.existEmailForChangePassword(email)) {
			SendEmail.sendEmail(email);
	//	}
	}  	
	
	@GET
	@Path("/{email}")
	@Produces (MediaType.APPLICATION_JSON)
	public boolean resetPassworda(@PathParam("email") String email) { 
		if (usr.existEmailForChangePassword(email)) {
			return true;
		}
		return false;
	} 
	
	
} 
