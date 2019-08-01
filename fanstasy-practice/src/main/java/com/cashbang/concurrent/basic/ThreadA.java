package com.cashbang.concurrent.basic;

/**
 * @Author: huangdejie
 * @Date: 2019/7/31
 */
public class ThreadA extends Thread {

    Object lock;

    public ThreadA(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock){
            System.out.println("ThreadA is started");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThreadA is End");
        }
    }
}
