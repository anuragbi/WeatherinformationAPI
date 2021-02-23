package com.test.weather.information.exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
@Provider
public class WeatherBusinessExceptionMapper implements ExceptionMapper<USZipCodeNotFoundException> {

	@Override
	public Response toResponse(USZipCodeNotFoundException exception) {
		// TODO Auto-generated method stub
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"status\":\"error\"");
		sb.append(",");
		sb.append("\"message\":\"Zip Code Not Found\"");
		sb.append("}");
		
		return Response.serverError().entity(sb.toString()).type(MediaType.APPLICATION_JSON).build();
	}

}
