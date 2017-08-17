package com.springboot.controller;

import com.springboot.domain.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wanglu on 17/8/17.
 */
@Controller
public class HomeController {

    @RequestMapping(value = {"/","/home"})
    public String index(Model model){
        Msg msg =  new Msg("测试标题","测试内容","额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "home";
    }
}
