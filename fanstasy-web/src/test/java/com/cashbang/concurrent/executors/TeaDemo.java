package com.cashbang.concurrent.executors;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @Author: huangdejie
 * @Date: 2019/5/17
 */
public class TeaDemo {
    /**
     * 泡茶:洗水壶、烧开水、泡茶  洗茶壶、洗水杯、拿茶叶
     *
     */

    public static void main(String[] args) {
        FutureTask<String> f2 = new FutureTask<String>(new T2Task());
        FutureTask<String> f1 = new FutureTask<String>(new T1Task(f2));
        Thread thread1 = new Thread(f1);
        thread1.start();
        Thread thread2 = new Thread(f2);
        thread2.start();
    }

    /**
     * 线程t1:洗水壶、烧开水、泡茶
     */
    static class T1Task implements Callable<String>{

        FutureTask<String> t2;

        public T1Task(FutureTask<String> t2) {
            this.t2 = t2;
        }

        @Override
        public String call() throws Exception {
            System.out.println("T1洗水壶");
            Thread.sleep(1000l);
            System.out.println("T1烧开水");
            Thread.sleep(1000l);
            System.out.println("T1等待茶叶中");
            String result = t2.get();
            System.out.println("T1拿到茶叶，开始泡茶，茶叶："+result);
            return null;
        }
    }

    /**
     *洗茶壶、洗水杯、拿茶叶
     */
    static class T2Task implements Callable<String>{
        @Override
        public String call() throws Exception {
            System.out.println("***T2洗茶壶中....");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("***T2洗茶杯中....");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("***T2那茶叶中....");
            return "龙井茶";
        }
    }
}
