package com.inhouse.resource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.inhouse.dto.Event;
import com.inhouse.dto.EventList;
import com.inhouse.service.EventService;

@Path("/event")
@RequestScoped
public class EventResource {

  @Inject
  private EventService service;

  /** 
   * Get events list 
   * param Sting communityidsは long communityIdの カンマ区切りを想定
   */
  @GET
  @Path("/list")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getList(@QueryParam("communityids") final String communityIds) {
    ResponseBuilder rb = null;
    EventList result = service.getEventList(communityIds);
    rb = Response.status(Response.Status.OK).entity(result);

    return rb.build();
  }

  /** Get 1 event info */
  @GET
  @Path("/info")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getInfo() {
    ResponseBuilder rb = null;
    // Event result = Event.builder().hogeMessage("hello").fugaMessage("world!").build();
    rb = Response.status(Response.Status.OK).entity(null);

    return rb.build();
  }


    /** Post 1 event info */
    @POST
    @Path("/info")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postInfo(Event event) {
      ResponseBuilder rb = null;
      // Event result = Event.builder().hogeMessage("hello").fugaMessage("world!").build();
      rb = Response.status(Response.Status.OK).entity(null);
  
      return rb.build();
    }

    /** Update 1 event info */
    @PUT
    @Path("/info")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putInfo(Event event) {
      ResponseBuilder rb = null;
      // Event result = Event.builder().hogeMessage("hello").fugaMessage("world!").build();
      rb = Response.status(Response.Status.OK).entity(null);
  
      return rb.build();
    }
}
