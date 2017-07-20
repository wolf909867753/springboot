package com.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by wanglu-jf on 17/7/19.
 */
@SpringBootApplication
@MapperScan("com.springboot.dao")
public class Applicaiton extends SpringBootServletInitializer{

    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Applicaiton.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(Applicaiton.class,args);
    }
}

