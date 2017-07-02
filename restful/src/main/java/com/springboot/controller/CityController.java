package com.springboot.controller;

import com.springboot.domain.City;
import com.springboot.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wl on 2017/7/2.
 */
@RestController
@RequestMapping(value = "/city")
public class CityController {
    @Autowired
    private ICityService cityService;

    @RequestMapping(value = "/query/{id}" ,method = RequestMethod.GET)
    public City queryById(@PathVariable("id") int id){
        return this.cityService.queryById(id);
    }

    @RequestMapping(value = "/save" ,method = RequestMethod.GET)
    public City save(City city){
        city = new City();
//        city.setId(2L);
        city.setCityName("北京");
        city.setDescription("我爱北京天安门");
        city.setProvinceId(3L);
        this.cityService.save(city);
        return city;
    }
}
