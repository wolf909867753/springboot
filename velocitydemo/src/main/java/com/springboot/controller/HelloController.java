package com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wanglu-jf on 17/8/9.
 */

@Controller
public class HelloController {

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("user","zhangsan");
        return "index";
    }
}
