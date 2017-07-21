package com.springboot.controller;

import com.springboot.domain.City;
import com.springboot.service.ICityService;
import com.springboot.utils.customException.CustomerException;
import com.springboot.utils.customException.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanglu-jf on 17/7/18.
 */
@RestController
public class CityController {
    @Autowired
    private ICityService cityService;

    @RequestMapping(value = "/api/city/{id}")
    public ResultMsg findOneCity(Model model, @PathVariable("id") Long id) throws CustomerException{
        ResultMsg msg = null;
        try {
            City city = cityService.findCityById(id);
            List<Object> citys = new ArrayList<Object>();
            citys.add(city);
            msg = new ResultMsg(citys);
        } catch (CustomerException e) {
           throw new CustomerException(e.getMessage(),e);
        }
        return msg;
    }

    @RequestMapping(value = "/api/city")
    public String findAllCity(Model model) {
        List<City> cityList = null;
        try {
            cityList = cityService.findAllCity();
        } catch (CustomerException e) {
            e.printStackTrace();
        }
        model.addAttribute("cityList",cityList);
        return "cityList";
    }

    @RequestMapping(value = "/api/save/")
    public String save(){
        City city = new City();
        city.setCityName("北京");
        city.setProvinceId(2L);
        city.setDescription("我爱北京天安门");
        List<City> allCity = null;
        try {
            allCity = cityService.findAllCity();
        } catch (CustomerException e) {
            e.printStackTrace();
        }
        long id = 0;
        if(allCity.size()>0){
            id = allCity.size()+ 1;
        }
        city.setId(id);
        try {
            cityService.saveCity(city);
        } catch (CustomerException e) {
            e.printStackTrace();
        }
        return "redirect:/api/city";
    }

    @RequestMapping(value = "/api/index")
    public String index(){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
        return "index";
    }
}
