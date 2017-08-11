package com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by wanglu-jf on 17/8/8.
 */
@Controller
public class HelloWorldController{

    @GetMapping(value = "/index")
    public String helloWorld(Model model){
        model.addAttribute("user","zhangsan");
        return "index";
    }
}
