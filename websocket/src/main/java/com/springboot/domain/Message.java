package com.springboot.domain;

/**
 * Created by wanglu-jf on 17/8/30.
 */
public class Message {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Message{" +
                "name='" + name + '\'' +
                '}';
    }
}
