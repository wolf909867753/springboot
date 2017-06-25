package com.springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by wl on 2017/6/18.
 */

/**
 *Spring Boot 配置文件 使用application.yml
 *测试类参考test目录下的YMLpropertiesTest.java
 *
 * 注意：当reources中同时存在application.properties ，application.yml文件时，spring优先启动application.properties配置文件
 */
@Component
public class HomePropertiesYML {

    /**
     * 省份
     */
    @Value("${home.province}")
    private String province;

    /**
     * 城市
     */
    @Value("${home.city}")
    private String city;

    /**
     * 描述
     */
    @Value("${home.desc}")
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
        return "HomePropertiesYML{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
