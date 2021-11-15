package com.inhouse.resource;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.inhouse.dto.Album;
import com.inhouse.dto.Photo;
import com.inhouse.service.AlbumService;

@Path("/album")
public class AlbumResource {

    @Inject
    private AlbumService service;
  
    /** 
     * Get Album list 
     * param Sting communityId
     */
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlbumList(@QueryParam("communityid") final Long communityId) {
      ResponseBuilder rb = null;
  
      if(Objects.isNull(communityId)|| communityId < 1){
        rb = Response.status(Response.Status.BAD_REQUEST);
        return rb.build();
      } 
  
      List<Album> result = service.getAlbumListByCommunityId(communityId);
      rb = Response.status(Response.Status.OK).entity(result);
  
      return rb.build();
    }    

      /** 
     * Get Album list 
     * param Sting communityId
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlbum(@PathParam("id") final Long albumId) {
      ResponseBuilder rb = null;
  
      if(Objects.isNull(albumId)|| albumId < 1){
        rb = Response.status(Response.Status.BAD_REQUEST);
        return rb.build();
      } 
  
      List<Photo> result = service.getPhotoListByAlbumId(albumId);
      rb = Response.status(Response.Status.OK).entity(result);
  
      return rb.build();
    }    
}
