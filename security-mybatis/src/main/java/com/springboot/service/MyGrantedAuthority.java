package com.springboot.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * Created by wanglu-jf on 17/8/28.
 */
@Component
public class MyGrantedAuthority implements GrantedAuthority{
    private String url;
    private String method;

    public String getPermissionUrl() {
        return url;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.url = permissionUrl;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public MyGrantedAuthority() {

    }

    public MyGrantedAuthority(String url, String method) {
        this.url = url;
        this.method = method;
    }

    @Override
    public String getAuthority() {
//        String str = this.url + ";" + this.method;
//        System.out.println("======="+str);
        return this.url + ";" + this.method;
    }
}
