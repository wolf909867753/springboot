package com.springboot.service;

import com.springboot.domain.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by wanglu-jf on 17/8/16.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void findByName() throws Exception {
        SysUser user = userService.findByName("wolf");
        System.out.println("[user====]" + user);

    }

}