package com.springboot.service.impl;

import com.springboot.domain.City;
import com.springboot.domain.User;
import com.springboot.service.IUserService;
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
public class UserServiceImplTest {
    @Autowired
    private IUserService userService;

    /**
     * 不支持分布式事务
     * @throws Exception
     */
    @Test
    public void save() throws Exception {
        City city = new City();
        city.setProvinceId(5L);
        city.setCityName("北京-jpa-mutil");
        city.setDescription("我爱北京天安门-jpa-mutil");
        User user = new User();
        user.setUserName("张三");
        user.setDescription("张三-jpa-mutil");
        this.userService.save(city,user);
    }

}