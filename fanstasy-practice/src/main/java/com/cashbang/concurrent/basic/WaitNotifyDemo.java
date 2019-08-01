package com.cashbang.concurrent.basic;

import java.util.concurrent.TimeUnit;

/**
 * @Author: huangdejie
 * @Date: 2019/7/31
 */
public class WaitNotifyDemo {

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        ThreadA threadA = new ThreadA(lock);
        threadA.start();
        ThreadB threadB = new ThreadB(lock);
        threadB.start();
        TimeUnit.SECONDS.sleep(5);
    }

}
