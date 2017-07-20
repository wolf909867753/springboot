package com.springboot.controller;

import com.springboot.domain.City;
import com.springboot.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by wanglu-jf on 17/7/18.
 */
@Controller
public class CityController {
    @Autowired
    private ICityService cityService;

    @RequestMapping(value = "/api/city/{id}", method = RequestMethod.GET)
    public String findOneCity(Model model, @PathVariable("id") Long id) {
        model.addAttribute("city", cityService.findCityById(id));
        return "city";
    }

    @RequestMapping(value = "/api/city", method = RequestMethod.GET)
    public String findAllCity(Model model) {
        List<City> cityList = cityService.findAllCity();
        model.addAttribute("cityList",cityList);
        return "cityList";
    }

    @RequestMapping(value = "/api/save/", method = RequestMethod.GET)
    public String save(){
        City city = new City();
        city.setCityName("北京");
        city.setProvinceId(2L);
        city.setDescription("我爱北京天安门");
        List<City> allCity = cityService.findAllCity();
        long id = 0;
        if(allCity.size()>0){
            id = allCity.size()+ 1;
        }
        city.setId(id);
        cityService.saveCity(city);
        return "redirect:/api/city";
    }

    @RequestMapping(value = "/api/index", method = RequestMethod.GET)
    public String index(){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
        return "index";
    }
}
