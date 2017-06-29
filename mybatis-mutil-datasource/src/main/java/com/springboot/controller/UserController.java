package com.springboot.controller;

import com.springboot.domain.User;
import com.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wanglu-jf on 17/6/28.
 */
@RestController
@RequestMapping("user/")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value="query/{id}",method = RequestMethod.GET)
    public User queryById(@PathVariable("id") int id){
        User user = this.userService.queryById(id);
        return user;
    }
}
