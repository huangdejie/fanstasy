package com.cashbang.designpattern.factory.work.simple;

/**
 * @Author: huangdejie
 * @Date: 2019/5/25
 */
public class LaptopComputer implements Computer {

    @Override
    public void create() {
        System.out.println("笔记本电脑已创建");
    }
}
