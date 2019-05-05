package com.cashbang.concurrent.eassy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: huangdejie
 * @Date: 2019/4/4
 */
public class TestAdd {

    private static Logger logger = LoggerFactory.getLogger(TestAdd.class);

    private int count;

    private void add(){
        int i = 0;
        while (i++<100){
            setCount(getCount()+1);

        }
    }

    public synchronized int getCount() {
        return count;
    }

    public synchronized void setCount(int count) {
        this.count = count;
    }

    public static void main(String[] args) {
        TestAdd testAdd1 = new TestAdd();
        TestAdd testAdd2 = new TestAdd();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                testAdd1.add();
                logger.info("线程1数值"+testAdd1.getCount());
                logger.info("线程1循环中......");
                while (true){

                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String now = simpleDateFormat.format(date);
                    if(now.equals("2019-04-04 16:12:50")){
                        break;
                    }
                }
                logger.info("线程1循环结束......");
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                testAdd1.add();
                logger.info("线程2的数值"+testAdd1.getCount());
            }
        });
        thread1.start();
        thread2.start();

    }
}
