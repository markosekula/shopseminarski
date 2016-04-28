package rs.Seminarski.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import rs.Seminarski.model.ErrorClass;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException exception) {
		ErrorClass error = new ErrorClass(exception.getMessage(), 404 , "Nothing!dy");
		return Response.status(Status.NOT_FOUND).entity(error).build();
	}

}
