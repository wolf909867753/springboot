package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wl on 2017/6/17.
 */
/**
 * 一个最简单的Web应用 测试类参考test目录下的HelloWorldTest.java
 * 使用Spring Boot框架可以大大加速Web应用的开发过程，首先在Maven项目依赖中引入spring-boot-starter-web
 *
 * @author wanglu-jf
 *  运行应用：mvn spring-boot:run或在IDE中运行main()方法，
 *  在浏览器中访问http://localhost:8080，Hello World!就出现在了页面中。
 *  只用了区区十几行Java代码，一个Hello World应用就可以正确运行了，那么这段代码究竟做了什么呢？
 *  们从程序的入口SpringApplication.run(HelloWorld.class, args);开始分析：
 *  SpringApplication是Spring Boot框架中描述Spring应用的类，
 *  它的run()方法会创建一个Spring应用上下文（Application Context）。另一方面它会扫描当前应用类路径上的依赖，
 *  例如本例中发现spring-webmvc（由 spring-boot-starter-web传递引入）在类路径中，那么Spring Boot会判断这是一个Web应用，
 *  并启动一个内嵌的Servlet容器（默认是Tomcat）用于处理HTTP请求。
 *  Spring WebMvc框架会将Servlet容器里收到的HTTP请求根据路径分发给对应的@Controller类进行处理，
 *  @RestController是一类特殊的@Controller，它的返回值直接作为HTTP Response的Body部分返回给浏览器。
 *  @RequestMapping注解表明该方法处理那些URL对应的HTTP请求，也就是我们常说的URL路由（routing)，请求的分发工作是有Spring完成的。
 *  例如上面的代码中http://localhost:8080/根路径就被路由至helloWorld()方法进行处理。
 *  如果访问http://localhost:8080/hello，则会出现404 Not Found错误，因为我们并没有编写任何方法来处理/hello请求。
 *
 * @SpringBootApplication -----Spring Boot应用启动类
 * 在本例中也可以去掉@SpringBootApplication 直接使用以下代码也可以运行
 * Created by bysocket on 16/4/26.
    @SpringBootApplication
    public class HelloWorldTest {
        public static void main(String[] args) {
            SpringApplication.run(HelloWorldTest.class,args);
        }
    }
 * 如果将@RestController ----> @Controller，运行main method将会报以下Error
 * Whitelabel Error Page
    This application has no explicit mapping for /error, so you are seeing this as a fallback.
    Sat Jun 17 14:30:28 CST 2017
    There was an unexpected error (type=Not Found, status=404).
    No message available
 *
 */
@RestController
//@SpringBootApplication
public class HelloWorld {

    @RequestMapping("/")
    public String helloWorld() {
        return "Hello World!";
    }

//    public static void main(String[] args) {
//        SpringApplication.run(HelloWorld.class,args);
//    }
}
