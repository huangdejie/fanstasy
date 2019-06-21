package com.cashbang.designpattern.factory.work.simple;

/**
 * @Author: huangdejie
 * @Date: 2019/5/25
 */
public class DesktopComputer implements Computer {

    @Override
    public void create() {
        System.out.println("台式电脑已创建");
    }
}
