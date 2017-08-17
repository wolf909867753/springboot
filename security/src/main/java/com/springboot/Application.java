package com.springboot;

import com.springboot.domain.SysUser;
import com.springboot.service.UserService;
import com.springboot.utils.ApplicationContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by wanglu-jf on 17/8/14.
 */
@SpringBootApplication
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
        SpringApplication app = new SpringApplication(Application.class);

        ApplicationContextUtil.applicationContext = app.run(args);//初始化上下文
        /*


        suserService.update(su);//运行一次后记得注释这段重复加密会无法匹配*/
        UserService userService = (UserService) ApplicationContextUtil.applicationContext.getBean("userService");
        SysUser sysUser = userService.findByName("admin");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);//将密码加密 可以先设置初始密码：000000
        sysUser.setPassword(bCryptPasswordEncoder.encode(sysUser.getPassword()));
        logger.info("[Application][main][result:[password={}]]",sysUser.getPassword());
//        userService.update(sysUser);




    }

}
