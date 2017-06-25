package com.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by wl on 2017/6/18.
 */
@SpringBootApplication
public class UserPropertiesTest implements CommandLineRunner {

    @Autowired
    private UserProperties userProperties;
    @Override
    public void run(String... strings) throws Exception {
        System.out.println(">>>>>>>   "+userProperties.toString());;
    }

    public static void main(String[] args) {
        SpringApplication.run(UserPropertiesTest.class,args);
    }
}
