package rs.Seminarski.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import rs.Seminarski.model.ErrorClass;


	
	@Provider
	public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

		@Override
		public Response toResponse(Throwable exception) {
			
			ErrorClass error = new ErrorClass(exception.getMessage(), 401, "Nothing!");
			//System.out.println(exception.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(error)
					.build();
		}


}