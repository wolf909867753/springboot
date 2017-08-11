package com.springboot;

import com.springboot.domain.City;
import com.springboot.service.ICityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by wanglu-jf on 17/8/11.
 */
@SpringBootTest(classes = Application.class)
@RunWith(value = SpringRunner.class)
public class ApplicationTest {

    @Autowired
    private ICityService cityService;

    @Test
    public void queryOne(){
        City city = this.cityService.findById(2L);
        System.out.println(">>>>>"+city);
    }

    @Test
    public void saveCity(){
        City city = new City();
        List<City> list = this.cityService.findAll();
        city.setId(Long.valueOf(list.size()+1));
        city.setDescription("我爱北京天安门");
        city.setProvinceId(4L);
        city.setCityName("潍坊市");
        City city1 = cityService.saveCity(city);
        System.out.println(">>>>>"+city1);
    }

    @Test
    public void updateCity(){
        City city = this.cityService.findById(2L);
        city.setDescription("我爱北京天安门");
        city.setProvinceId(4L);
        city.setCityName("潍坊市");
        City city1 = this.cityService.updateById(city);
        System.out.println(">>>>>"+city1);
    }


}
