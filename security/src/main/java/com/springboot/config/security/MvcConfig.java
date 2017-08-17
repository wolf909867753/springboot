package com.springboot.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by wanglu-jf on 17/8/17.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(MvcConfig.class);
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        logger.info("[MvcConfig][addViewControllers]invoke...start");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        logger.info("[MvcConfig][addViewControllers][result:{}]...end",registry);
    }
}
