package org.yash.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("injectdemo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN) 
public class InjectDemoResource {

	@GET
	@Path("annotations")
	public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
											@HeaderParam("CustomHeaderValue") String header,
											@CookieParam("Name") String cookie) {
		/*
		 * Request -
		 * http://localhost:8080/messenger/webapi/injectdemo/annotations;param=namaste
		 * CustomHeaderValue = Test Value
		 * 
		 * Output -
		 * Matrix Param : namaste
		 */
		return "Matrix Param : " + matrixParam + "\n" +
		 		"CustomHeaderValue : " + header + "\n" +
		 		"Cookie Param : " + cookie;
	}
	
	@GET
	@Path("context")
	public String getParamsUsingContext(@Context UriInfo uriInfo,
										@Context HttpHeaders headers) {
		
		String path = uriInfo.getAbsolutePath().toString();
		String cookies = headers.getCookies().toString();
		return "Path : " + path + "\n" +
				"Cookies : " + cookies;
	}
}
