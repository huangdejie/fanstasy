package com.cashbang.concurrent.executors;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @Author: huangdejie
 * @Date: 2019/5/17
 */
public class CallableDemo {

    static class SumTask implements Callable<Long>{
        @Override
        public Long call() throws Exception {
            long sum = 0;
            for(int i = 0;i<10000;i++){
                sum+=i;
            }
            return sum;
        }
    }

    public static void main(String[] args) throws Exception{
        System.out.println("时间开始"+System.nanoTime());
        FutureTask<Long> futureTask = new FutureTask<Long>(new SumTask());
        Executor executors = Executors.newSingleThreadExecutor();
        executors.execute(futureTask);
        System.out.println(futureTask.get());
        System.out.println("时间结束"+System.nanoTime());
    }


}
