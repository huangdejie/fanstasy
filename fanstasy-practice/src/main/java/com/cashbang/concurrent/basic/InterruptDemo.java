package com.cashbang.concurrent.basic;

import java.util.concurrent.TimeUnit;

/**
 * @Author: huangdejie
 * @Date: 2019/7/31
 */
public class InterruptDemo {

    private static int i;


    public static void main(String[] args) throws InterruptedException {
//        testInterrupt();
        testInterrupted();
    }

    /**
     * 当其他线程通过嗲用当前线程的interrupt方法，表示向当前线程打个招呼，告诉他可以中断线程的执行了，
     * 至于什么时候中断，取决于当前线程自己
     */
    public static void testInterrupt() throws InterruptedException {
        Thread thread = new Thread(()->{
            while (!Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println(i);
        },"interrupt_demo");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }

    /**
     * Thread.interrupted对设置中断标识的线程进行复位
     * @throws InterruptedException
     */
    public static void testInterrupted() throws InterruptedException {
        Thread thread = new Thread(()->{
            while (true){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("before:"+Thread.currentThread().isInterrupted());
                    Thread.interrupted();
                    System.out.println("after:"+Thread.currentThread().isInterrupted());
                }
            }
        },"interruptedDemo");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }

}
