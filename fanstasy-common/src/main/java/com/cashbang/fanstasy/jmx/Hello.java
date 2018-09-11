package com.cashbang.fanstasy.jmx;

/**
 * @Author: huangdejie
 * @Date: 2018/9/7 0007 上午 9:32
 */
public class Hello implements HelloMBean {

    private String name;

    @Override
    public void printHello() {
        System.out.println("Hello World,"+name);
    }

    @Override
    public void printHello(String whoName) {
        System.out.println("Hello,"+whoName);
    }

    @Override
    public void setName(String name) {
        this.name =name;
    }

    @Override
    public String getName() {
        return name;
    }
}
