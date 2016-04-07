package rs.Seminarski.authentication;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import io.jsonwebtoken.Jwts;

@Secure
@Provider
@Priority (Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		String authenticationheader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (authenticationheader == null || !authenticationheader.startsWith("Bearer")) {
			throw new NotAuthorizedException("Nije u hederu!");
			
		} 
		
		 String token = authenticationheader.substring("Bearer ".length()).trim();
		
		try {
			 // Validate the token
            validateToken(token);
			
		} catch (Exception e) {
			requestContext.abortWith (Response.status(Response.Status.UNAUTHORIZED).build());
		}
	}

	private void validateToken(String token) {
		Jwts.parser().setSigningKey("kljuc").parseClaimsJws(token);
		
	}
	

}
