package com.inhouse.resource;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.inhouse.dto.UserInfo;
import com.inhouse.service.UserService;

@Path("/user")
public class UserResource {

  @Inject
  private UserService service;

  /** 
   * Get UserInfo list 
   * param Sting communityId
   */
  @GET
  @Path("/joiner")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getJoiner(@QueryParam("communityid") final Long communityId) {
    ResponseBuilder rb = null;

    if(Objects.isNull(communityId)|| communityId < 1){
      rb = Response.status(Response.Status.BAD_REQUEST);
      return rb.build();
    } 

    List<UserInfo> result = service.getUserInfoListByCommunityId(communityId);
    rb = Response.status(Response.Status.OK).entity(result);

    return rb.build();
  }    
}
