package com.inhouse.application.controller;

import java.util.List;

import com.inhouse.application.resource.community.GetCommunitiesListInputDto;
import com.inhouse.application.resource.community.GetCommunitiesListOutputDto;
import com.inhouse.application.resource.community.PostCommunityInputDto;
import com.inhouse.application.resource.community.PostCommunityOutputDto;
import com.inhouse.domain.object.Community;
import com.inhouse.domain.service.CommunityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

/**
 * コミュニティ コントローラ
 */
@Api(value="community api", description="Manage Community")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/community")
public class CommunityController {

    @Autowired
    private final CommunityService service;


    @ApiOperation(value ="GET Communities List", notes="return 200")
    @ApiResponses({
            @ApiResponse(code = 200, message = "return Communities List", response = GetCommunitiesListOutputDto.class)
    })
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public GetCommunitiesListOutputDto GetGommunitiesList(@RequestParam("keyword") String keyword) {
        GetCommunitiesListInputDto input = GetCommunitiesListInputDto.builder()
                                                                    .keyword(keyword)
                                                                    .build();
        GetCommunitiesListOutputDto response = new GetCommunitiesListOutputDto();
        
        List<Community> communityList = service.getCommunityList(input.toDomain());

        response.setCommunityList(communityList);
        return response;
    }


    @ApiOperation(value ="Post Community", notes="insert community")
    @ApiResponses({
            @ApiResponse(code = 200, message = "return community_id", response = PostCommunityOutputDto.class)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public PostCommunityOutputDto CreateCommunity(@RequestBody @Validated PostCommunityInputDto body) {
        PostCommunityOutputDto response = new PostCommunityOutputDto();

        System.out.println("body: " + body.toString());                                    
        int communityId = service.createCommunity(body.toDomain());

        response.setCommunityId(communityId);
        return response;
    }


  
}