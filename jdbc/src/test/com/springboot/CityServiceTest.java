package com.springboot;

import com.springboot.domain.City;
import com.springboot.service.ICityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by wanglu-jf on 17/8/11.
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class CityServiceTest {

    @Autowired
    private ICityService cityService;

    @Test
    public void saveTest(){
        City city = new City();
        Integer count = this.cityService.countAllCitys();
        city.setId(Long.valueOf(count+1));
        city.setDescription("我爱北京天安门");
        city.setProvinceId(3L);
        city.setCityName("潍坊市");
        cityService.create(city);
    }

    @Test
    public void countAllCitysTest(){
        System.err.println(">>>>>>>>:"+cityService.countAllCitys());
    }
}
