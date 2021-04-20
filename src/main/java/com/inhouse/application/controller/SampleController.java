package com.inhouse.application.controller;

import com.inhouse.application.resource.sample.HelloWorldInputDto;
import com.inhouse.application.resource.sample.HelloWorldOutputDto;
import com.inhouse.domain.object.Sample;
import com.inhouse.domain.service.SampleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

/**
 * サンプルコントローラ
 */
@Api(value="sample api", description="Operate hoge")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/sample")
public class SampleController {

    @Autowired
    private final SampleService service;

    /**
     * サンプル処理
     *
     * @param userBody リクエストボディ
     * @return 更新後のユーザ
     */
    @ApiOperation(value ="HelloWorld", notes="insert/update into sample")
    @ApiResponses({
            @ApiResponse(code = 200, message = "hello wolrdを返す", response = HelloWorldOutputDto.class)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public HelloWorldOutputDto helloWorld(@RequestBody @Validated HelloWorldInputDto body) {
        HelloWorldOutputDto response = new HelloWorldOutputDto();

        Sample sample = service.hello(body.toDomain());
        response.setCount(sample.getCount());
        response.setMessage("Hello " + sample.getName() + "!");
        return response;
    }


    @ApiOperation(value ="check", notes="return 200")
    @ApiResponses({
            @ApiResponse(code = 200, message = "200を返すだけ")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void check() {
    }

}