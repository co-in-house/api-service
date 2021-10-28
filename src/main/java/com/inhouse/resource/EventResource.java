package com.inhouse.resource;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.json.JsonObject;
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
import com.inhouse.dto.Result;
import com.inhouse.service.EventService;
import com.inhouse.util.ConsoleLogger;

@Path("/event")
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

    if(Objects.isNull(communityIds)|| communityIds.isEmpty() || communityIds.trim() == ""){
      rb = Response.status(Response.Status.BAD_REQUEST);
      return rb.build();
    } 

    ArrayList<Long> communityIdList = new ArrayList<Long>();
    
    try {
      ArrayList<Long> tmpIdList = new ArrayList<Long>();
      for (String str : communityIds.split(",")) {        
        tmpIdList.add(Long.parseLong(str));
       }
       communityIdList = (ArrayList<Long>) tmpIdList.stream().distinct().collect(Collectors.toList());
    } catch (Exception e) {
      ConsoleLogger.error(e.getMessage());
      rb = Response.status(Response.Status.BAD_REQUEST);
      return rb.build();
    }

    EventList result = service.getEventList(communityIdList);
    rb = Response.status(Response.Status.OK).entity(result);

    return rb.build();
  }

    /** Post 1 event info */
    @POST
    @Path("/info")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postInfo(JsonObject json) {
      ResponseBuilder rb = null;
      Event event;
      try {
        event = jsonToEventForPost(json);
      } catch (Exception e) {
        ConsoleLogger.debug(e.getMessage());
        rb = Response.status(Response.Status.BAD_REQUEST);
        return rb.build();
      }
      boolean isSqlSuccess = service.postEvent(event);
      if(isSqlSuccess){
        Result result = Result.builder().result("success").build();
        rb = Response.status(Response.Status.OK).entity(result);
      } else {
        rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
      }
      
      return rb.build();
    }

    /** Update 1 event info */
    @PUT
    @Path("/info")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putInfo(JsonObject json) {
      ResponseBuilder rb = null;
      Event event;
      try {
        event = jsonToEventForPut(json);
      } catch (Exception e) {
        ConsoleLogger.debug(e.getMessage());
        rb = Response.status(Response.Status.BAD_REQUEST);
        return rb.build();
      }
      boolean isSqlSuccess = service.putEvent(event);
      if(isSqlSuccess){
        Result result = Result.builder().result("success").build();
        rb = Response.status(Response.Status.OK).entity(result);
      } else {
        rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
      }
      
      return rb.build();
    }


    private Event jsonToEventForPost(JsonObject json) throws ClassCastException, NullPointerException {
      return Event.builder()
                  .communityId(Long.valueOf(json.getInt("communityId")))
                  .title(json.getString("title"))
                  .start(json.getString("start"))
                  .end(json.getString("end"))
                  .location(json.getString("location"))
                  .description(json.getString("description"))
                  .thumbnailImg(json.getString("thumbnailImg"))
                  .build();
    }

    private Event jsonToEventForPut(JsonObject json) throws ClassCastException, NullPointerException {
      return Event.builder()
                  .eventId(Long.valueOf(json.getInt("eventId")))
                  .title(json.getString("title"))
                  .start(json.getString("start"))
                  .end(json.getString("end"))
                  .location(json.getString("location"))
                  .description(json.getString("description"))
                  .thumbnailImg(json.getString("thumbnailImg"))
                  .build();
      
    }
}