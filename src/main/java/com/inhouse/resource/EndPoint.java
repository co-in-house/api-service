package com.inhouse.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/sample")
public class EndPoint {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String start() {
    System.out.println("access.");
    return "executed.";
  }
}
