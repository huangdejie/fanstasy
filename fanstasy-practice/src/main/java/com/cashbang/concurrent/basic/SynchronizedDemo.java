package com.cashbang.concurrent.basic;

import java.io.IOException;

/**
 * @Author: huangdejie
 * @Date: 2019/7/31
 */
public class SynchronizedDemo {

    static Integer count=0;
    public static void incr(){
        synchronized (count) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        for(int i=0;i<1000;i++){
            new Thread(()->SynchronizedDemo.incr()).start();
        }
        Thread.sleep(5000);
        System.out.println("result:"+count);
    }

}
