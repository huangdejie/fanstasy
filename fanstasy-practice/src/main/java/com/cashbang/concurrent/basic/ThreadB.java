package com.cashbang.concurrent.basic;

/**
 * @Author: huangdejie
 * @Date: 2019/7/31
 */
public class ThreadB extends Thread {

    Object lock;

    public ThreadB(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock){
            System.out.println("ThreadB is started");
            lock.notify();
            System.out.println("ThreadB is end");
        }
    }
}
