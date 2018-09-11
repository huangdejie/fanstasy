package com.fanstasy.thread;

/**
 * @Author: huangdejie
 * @Date: 2018/9/11 0011 上午 10:13
 */
public class Thread1 extends Thread {

    private int count = 5;

    public Thread1(String name){
        this.setName(name);
    }

    public Thread1(){

    }

    @Override
    synchronized public void run() {
        super.run();
//        while (count > 0){
        if(count <= 0){
            System.out.println("出错啦---");
            return;
        }
            count --;
            System.out.println(Thread.currentThread().getName()+"--count:"+count);
//        }
//        System.out.println(Thread.currentThread().getName());
    }
}
