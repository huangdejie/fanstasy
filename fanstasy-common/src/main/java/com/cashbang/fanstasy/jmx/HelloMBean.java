package com.cashbang.fanstasy.jmx;

/**
 * @Author: huangdejie
 * @Date: 2018/9/7 0007 上午 9:30
 */
public interface HelloMBean {

    String getName();
    void setName(String name);
    void printHello();
    void printHello(String whoName);
}
