package com.springboot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by wl on 2017/6/18.
 */

/**
 * Spring Boot 配置文件 使用application.properties
 * 测试类参考test目录下的PropertiesTest.java
 *一、自动配置
 *   Spring Boot 提供了对应用进行自动化配置。相比以前 XML 配置方式，很多显式方式申明是不需要的。二者，大多数默认的配置足够实现开发功能，从而更快速开发。
 *   什么是自动配置？
 *   Spring Boot 提供了默认的配置，如默认的 Bean ，去运行 Spring 应用。它是非侵入式的，只提供一个默认实现。
 *   大多数情况下，自动配置的 Bean 满足了现有的业务场景，不需要去覆盖。但如果自动配置做的不够好，需要覆盖配置。
 *   比如通过命令行动态指定某个 jar ，按不同环境启动。那怎么办？这里先要考虑到配置的优先级。
 *
 *   Spring Boot 不单单从 application.properties 获取配置，所以我们可以在程序中多种设置配置属性。按照以下列表的优先级排列：
 *  1.命令行参数
 *   2.java:comp/env 里的 JNDI 属性
 *   3.JVM 系统属性
 *   4.操作系统环境变量
 *   5.RandomValuePropertySource 属性类生成的 random.* 属性
 *   6.应用以外的 application.properties（或 yml）文件
 *    7.打包在应用内的 application.properties（或 yml）文件
 *    8.在应用 @Configuration 配置类中，用 @PropertySource 注解声明的属性文件
 *   9.SpringApplication.setDefaultProperties 声明的默认属性
 *
 *    可见，命令行参数优先级最高。这个可以根据这个优先级，可以在测试或生产环境中快速地修改配置参数值，而不需要重新打包和部署应用。
 *   还有第 6 点，根据这个在多 moudle 的项目中，比如常见的项目分 api 、service、dao 等 moudles，往往会加一个 deploy moudle 去打包该业务各个子 moudle，
 *   应用以外的配置优先。
 *
 * 二.@ConfigurationProperties(prefix="home")
 *  通过 @ConfigurationProperties(prefix = “home”) 注解，将配置文件中以 home 前缀的属性值自动绑定到对应的字段中。
 *  同是用 @Component 作为 Bean 注入到 Spring 容器中。
 *
 *  注意：当reources中同时存在application.properties ，application.yml文件时，spring优先启动application.properties配置文件
 */
@Component
@ConfigurationProperties(prefix="home")
public class HomeProperties {

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 描述
     */
    private String desc;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "HomeProperties{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
