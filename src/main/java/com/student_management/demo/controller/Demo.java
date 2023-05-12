package com.student_management.demo.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "演示接口")
@RestController
@RequestMapping("/my-api/demo")
public class Demo {
    @ApiOperation("Hello World接口")
    @GetMapping("/hello")
    public String hello() {
        return "Hello, world!";
    }

    @ApiOperation("Hello World2接口")
    @GetMapping("/hello2")
    public String hello2() {
        return "Hello, world!";
    }
}
