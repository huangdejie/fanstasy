package com.cashbang.fanstasy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * 代理对象不需要实现接口，但目标对象一定要实现接口
 * @Author: huangdejie
 * @Date: 2018/11/8
 */
public class ProxyFactory {

    private Object target;

    public ProxyFactory(Object target){
        this.target = target;
    }

    public Object getProxyInstance(){
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("开始");
                        Object proxyValue = method.invoke(target,args);
                        System.out.println("结束");
                        return proxyValue;
                    }
                }

        );
    }
}
