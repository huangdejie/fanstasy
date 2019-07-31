package com.cashbang.concurrent.gupao.thread_demo;

/**
 * @Author: huangdejie
 * @Date: 2019/7/30
 */
public class Request {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                '}';
    }
}
