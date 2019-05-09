package com.cashbang.concurrent.eassy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: huangdejie
 * @Date: 2019/4/4
 */
public class VolatileTest {

    private volatile int count = 0;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void add1(){
        try {
            Thread.sleep(5000l);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        int i = count+1;
        logger.info("thread1读取的值"+i);
    }

    public void add2(){
        try {
            Thread.sleep(5000l);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        int i = count+2;
        logger.info("thread2读取的值为"+i);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static void main(String[] args) {
        VolatileTest volatileTest = new VolatileTest();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                volatileTest.add2();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                volatileTest.add1();
            }
        });
        thread1.start();
        thread2.start();
    }
}
