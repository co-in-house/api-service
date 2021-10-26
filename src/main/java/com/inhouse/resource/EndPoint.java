package com.inhouse.resource;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.inhouse.dto.GetHelloOutputDto;
import com.inhouse.util.ConsoleLogger;



@Path("/sample")
@RequestScoped
public class EndPoint {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response start() {
    ResponseBuilder rb = null;
    ConsoleLogger.info("これはinfo！");
    ConsoleLogger.error("これはerr!");
    
    GetHelloOutputDto result = GetHelloOutputDto.builder().hogeMessage("hello").fugaMessage("world!").build();
    
    rb = Response.status(Response.Status.OK).entity(result);

    return rb.build();
  }
}
