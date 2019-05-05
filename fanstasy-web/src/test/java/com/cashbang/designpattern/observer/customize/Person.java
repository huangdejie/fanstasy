package com.cashbang.designpattern.observer.customize;

/**
 * @Author: huangdejie
 * @Date: 2019/3/7
 */
public class Person implements Observer {

    private String name;

    public Person(String name){
        this.name = name;
    }

    @Override
    public void update(float price) {
        System.out.println(name+"关注的杯子价格已更新为"+price);
    }
}
