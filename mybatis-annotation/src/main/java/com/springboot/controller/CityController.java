package com.springboot.controller;

import com.springboot.domain.City;
import com.springboot.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wanglu-jf on 17/6/28.
 */
@RestController
@RequestMapping("city/")
public class CityController {

    @Autowired
    private ICityService cityService;

    @RequestMapping(value = "query/{id}",method = RequestMethod.GET)
    public City queryById(@PathVariable("id") int id){
        return this.cityService.queryById(id);
    }
}
