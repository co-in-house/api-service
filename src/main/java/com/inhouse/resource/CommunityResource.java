
package com.inhouse.resource;

import java.util.Objects;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.inhouse.dto.CommunityList;
import com.inhouse.service.CommunityService;

@Path("/community")
public class CommunityResource {

  @Inject
  private CommunityService service;

  /** 
   * Get Joined Community list 
   * param Sting user_id
   */
  @GET
  @Path("/joined")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getList(@QueryParam("userid") final Long userId) {
    ResponseBuilder rb = null;

    if(Objects.isNull(userId)|| userId < 1){
      rb = Response.status(Response.Status.BAD_REQUEST);
      return rb.build();
    } 

    CommunityList result = service.getCommunityListByUserId(userId);
    rb = Response.status(Response.Status.OK).entity(result);

    return rb.build();
  }

  /** 
   * Search Community list 
   * param Sting keyword
   */
  @GET
  @Path("/search")
  @Produces(MediaType.APPLICATION_JSON)
  public Response search(@QueryParam("keyword") final String keyword) {
    ResponseBuilder rb = null;

    if(Objects.isNull(keyword)|| keyword.isEmpty() || keyword.trim() == ""){
      rb = Response.status(Response.Status.BAD_REQUEST);
      return rb.build();
    } 

    CommunityList result = service.getCommunityListLikeKeyword(keyword);
    
    rb = Response.status(Response.Status.OK).entity(result);
    return rb.build();
  }

  
}