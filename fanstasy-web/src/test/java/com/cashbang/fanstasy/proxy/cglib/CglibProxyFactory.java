package com.cashbang.fanstasy.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: huangdejie
 * @Date: 2018/11/8
 */
public class CglibProxyFactory implements MethodInterceptor{

    /**
     * 目标对象
     */
    private Object target;

    public CglibProxyFactory(Object target){
        this.target = target;
    }

    public Object getProxyInstance(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("****上课前需备课*****");
        Object value = method.invoke(target,objects);
        System.out.println("****下课布置作业******");
        return value;
    }
}
