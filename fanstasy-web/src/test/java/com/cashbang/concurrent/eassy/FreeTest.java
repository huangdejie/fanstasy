package com.cashbang.concurrent.eassy;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: huangdejie
 * @Date: 2019/5/22
 * 随便写写的demo
 */
public class FreeTest {

    public static void main(String[] args) throws Exception{
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(500);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5,5,1000, TimeUnit.SECONDS,queue);
        Future<String> future = poolExecutor.submit(()->{
            System.out.println("6666");
            TimeUnit.SECONDS.sleep(5);
            return "hello";
        });
        String result = future.get();
        System.out.println(result);
    }

}
