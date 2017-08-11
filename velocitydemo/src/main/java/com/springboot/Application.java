package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by wanglu-jf on 17/8/9.
 */
@SpringBootApplication
public class Application /*implements CommandLineRunner*//*extends SpringBootServletInitializer*/{
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(Application.class);
//    }
//    @Autowired
//    private VelocityProperties velocityProperties;
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println(">>>>"+velocityProperties.toString());
//    }

//    @PostConstruct
//    public void initVelocityConfig(){
//        System.out.println(">>>>>>>>>>>"+velocityProperties);
//    }
}
