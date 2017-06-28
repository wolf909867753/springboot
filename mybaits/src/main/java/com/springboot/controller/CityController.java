package com.springboot.controller;

import com.springboot.domain.City;
import com.springboot.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wanglu-jf on 17/6/27.
 */
@RestController
@RequestMapping("city/")
public class CityController {

    @Autowired
    private ICityService cityService;

    @RequestMapping("query/{name}")
    public City queryByName(@PathVariable("name") String name){
        return this.cityService.queryCityByName(name);
    }

//    @RequestMapping(value = "/api/city", method = RequestMethod.GET)
//    public City findOneCity(@RequestParam(value = "cityName", required = true) String cityName) {
//        return cityService.findCityByName(cityName);
//    }

    @RequestMapping(value = "query",method = RequestMethod.POST)
    public City findByName(String name){
        System.out.println(">>>>>>>>>>>name:"+name);
        return this.cityService.queryCityByName(name);
    }


}
