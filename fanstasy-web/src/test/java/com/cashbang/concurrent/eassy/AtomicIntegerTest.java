package com.cashbang.concurrent.eassy;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: huangdejie
 * @Date: 2019/2/19
 */
public class AtomicIntegerTest {

    private static final int THREAD_COUNT = 20;
    private static AtomicInteger count = new AtomicInteger(0);

    public static void increase(){
        count.incrementAndGet();
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREAD_COUNT];
        for(int i = 0;i<THREAD_COUNT;i++){
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0;i<1000;i++){
                        increase();
                    }
                }
            });
            threads[i].start();
        }
        while (Thread.activeCount() > 1){
            Thread.yield();
        }
        System.out.println(count);
    }

}
