package com.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by wanglu-jf on 17/6/27.
 */
@SpringBootApplication
@MapperScan("com.springboot.dao")
public class Application /*implements ApplicationRunner*/{


//    @Override
//    public void run(ApplicationArguments applicationArguments) throws Exception {
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>ApplicationRunner start");
//    }

    public static void main(String[] args) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>Application start");
        SpringApplication.run(Application.class,args);
    }
}
