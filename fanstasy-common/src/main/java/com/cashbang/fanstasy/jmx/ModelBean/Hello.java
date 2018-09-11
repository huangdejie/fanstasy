package com.cashbang.fanstasy.jmx.ModelBean;

/**
 * @Author: huangdejie
 * @Date: 2018/9/7 0007 上午 11:36
 */
public class Hello {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printHello(){
        System.out.println("hello,"+name);
    }

    public void printHello(String whoName){
        System.out.println("hi,"+whoName);
    }
}
