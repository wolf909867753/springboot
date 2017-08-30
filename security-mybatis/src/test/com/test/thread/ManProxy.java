package com.test.thread;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wanglu-jf on 17/8/23.
 */
public class ManProxy implements InvocationHandler{

    private Object person;

    public ManProxy(Object person) {
        this.person = person;
    }

    public Iperson getInstance(){
        return (Iperson) Proxy.newProxyInstance(this.getClass().getClassLoader(), person.getClass().getInterfaces(), this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法执行之前！");
        Object obj = method.invoke(person, args);
        System.out.println("方法执行之后！");
        return obj;
    }


}
