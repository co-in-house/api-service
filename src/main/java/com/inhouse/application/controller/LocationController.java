package com.inhouse.application.controller;

import com.inhouse.application.resource.location.GetLocationListOutputDto;
import com.inhouse.domain.service.LocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

/**
 * 位置情報 コントローラ
 */
@Api(value="location api", description="location")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/location")
public class LocationController {

    @Autowired
    private final LocationService service;


    @ApiOperation(value ="Get Location", notes="get tag location")
    @ApiResponses({
            @ApiResponse(code = 200, message = "return tag locaation", response = GetLocationListOutputDto.class)
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public GetLocationListOutputDto GetTagList() {
        GetLocationListOutputDto response = new GetLocationListOutputDto();
        response.setLocationList(service.getList());
        return response;
    }

}