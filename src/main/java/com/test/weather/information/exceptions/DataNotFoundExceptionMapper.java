package com.test.weather.information.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	public DataNotFoundExceptionMapper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response toResponse(DataNotFoundException exception) {
		// TODO Auto-generated method stub
		return Response.status(Status.NOT_FOUND).build();
	}

}
