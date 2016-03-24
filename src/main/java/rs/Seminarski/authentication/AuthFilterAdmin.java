package rs.Seminarski.authentication;

import java.io.IOException;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import io.jsonwebtoken.Jwts;

public class AuthFilterAdmin implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
	
		String authenticationheaderadmin = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (authenticationheaderadmin == null || !authenticationheaderadmin.startsWith("Bearer")) {
			throw new NotAuthorizedException("Nije u hederu!");	
		}
		
		String token = authenticationheaderadmin.substring("Bearer".length()).trim();
		
		try {
			 // Validate the token
           validateToken(token);
			
		} catch (Exception e) {
			requestContext.abortWith (Response.status(Response.Status.UNAUTHORIZED).build());
		}
		
	}

	private void validateToken(String token) {
		Jwts.parser().require("admin", "1").setSigningKey("kljuc").parseClaimsJws(token);
		
	}
	
}
