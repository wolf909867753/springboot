package com.test.thread;

/**
 * Created by wanglu-jf on 17/8/23.
 */
public class Person implements Iperson {

    public Person(){//构造

    }

    private String name;

    public Person(String name){//构造

        this.name=name;

    }

    @Override
    public void say(String str) {
        System.err.println(name+" Hello:"+str);
    }
}
