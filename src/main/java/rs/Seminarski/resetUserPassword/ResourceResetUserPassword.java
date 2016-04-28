package rs.Seminarski.resetUserPassword;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/reset-password")
public class ResourceResetUserPassword {
	
	SendEmail sendemail = new SendEmail ();
	
	@GET
	@Path("/{email}")
	@Produces (MediaType.APPLICATION_JSON)
	public void resetUserPassword (@PathParam("email") String email) {
		
		SendEmail.sendEmail(email);
		
	}

}
