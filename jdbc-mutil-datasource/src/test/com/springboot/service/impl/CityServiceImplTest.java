package com.springboot.service.impl;

import com.springboot.domain.City;
import com.springboot.service.ICityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by wanglu-jf on 17/8/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CityServiceImplTest {

    @Autowired
    private ICityService cityService;

    /**
     * jdbc多数据源测试
     * 注意 --修改主键id value
     * @throws Exception
     */
    @Test
    public void save() throws Exception {
        City city = new City();
        city.setId(223L);
        city.setProvinceId(5L);
        city.setCityName("北京");
        city.setDescription("我爱北京天安门");
        this.cityService.save(city);
    }

}