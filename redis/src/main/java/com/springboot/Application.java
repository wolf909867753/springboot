package com.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by wanglu-jf on 17/7/20.
 */
@SpringBootApplication
@MapperScan("com.springboot.dao")
public class Application extends SpringBootServletInitializer {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
//        long l = System.nanoTime();
//        long time = new Date().getTime();
//        System.out.println(">>>>>>>>>l:"+l);
//        System.out.println(">>>>>>time:"+ TimeUnit.NANOSECONDS.toNanos(time));
//        TimeUnit.NANOSECONDS.toNanos(time);
    }
}
