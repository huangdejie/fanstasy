package com.cashbang.concurrent.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Author: huangdejie
 * @Date: 2019/5/17
 */
public class MyFirstThreadPool {

    private BlockingQueue<Runnable> workQueue;

    private List<WorkThread> threads = new ArrayList<>();

    MyFirstThreadPool(int poolSize,BlockingQueue<Runnable> workQueue){
        this.workQueue = workQueue;
        for(int idx = 0;idx<poolSize;idx++){
            WorkThread workThread = new WorkThread();
            workThread.start();
            threads.add(workThread);
        }
    }

    public void execute(Runnable command) throws InterruptedException{
        workQueue.put(command);
    }

    private class WorkThread extends Thread{
        @Override
        public void run(){
            while (true){
                try {
                    Runnable task = workQueue.take();
                    task.run();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingDeque<>(2);
        MyFirstThreadPool myFirstThreadPool = new MyFirstThreadPool(10,blockingQueue);
        myFirstThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("nihao");
            }
        });
    }

}
