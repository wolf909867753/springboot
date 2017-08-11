package com.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wanglu-jf on 17/8/10.
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

//    @ApiIgnore
    @GetMapping(value = "/hello")
    public String index() {
        return "Hello World";
    }

}
