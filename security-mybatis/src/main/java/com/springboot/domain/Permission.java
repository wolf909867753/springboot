package com.springboot.domain;

/**
 * 权限信息表
 * Created by wanglu-jf on 17/8/21.
 */
public class Permission {
    private int id;

    private String name;//权限名称

    private String description;//权限描述

    private String url;//授权链接

    private int pid;//父节点id

    private String method;//请求方法

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", pid=" + pid +
                ", method='" + method + '\'' +
                '}';
    }
}
