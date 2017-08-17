package com.springboot.utils;

import org.springframework.context.ApplicationContext;

/**
 * Created by wanglu-jf on 17/8/17.
 */
public class ApplicationContextUtil {

    public static ApplicationContext applicationContext = null;

    public static Object getBean(String str){
        return applicationContext.getBean(str);
    }
}
