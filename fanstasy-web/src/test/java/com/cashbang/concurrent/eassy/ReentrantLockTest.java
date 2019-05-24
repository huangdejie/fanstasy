package com.cashbang.concurrent.eassy;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: huangdejie
 * @Date: 2019/5/22
 */
public class ReentrantLockTest {

    private Lock reentrantLock = new ReentrantLock();

    private volatile int a = 0;

    public void lock(){
//        reentrantLock.lock();
        System.out.println("线程"+Thread.currentThread().getName()+"获得锁了");
        try {
            Thread.sleep(5000L);
            a++;
        }catch (Exception e){
            System.out.println("抛出异常");
        }finally {
            System.out.println("线程"+Thread.currentThread().getName()+"释放锁了");
//            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        Thread thread = new Thread(()->{
            reentrantLockTest.lock();
            try {

                Thread.sleep(5000L);
            }catch (Exception e){
                System.out.println("哈哈哈");
            }
            System.out.println("线程"+Thread.currentThread().getName()+"获得的值"+reentrantLockTest.getA());
        });
        Thread thread2 = new Thread(()->{
            reentrantLockTest.lock();
            try {

                Thread.sleep(1000L);
            }catch (Exception e){
                System.out.println("哈哈哈5");
            }
            System.out.println("线程"+Thread.currentThread().getName()+"获得的值"+reentrantLockTest.getA());
        });
        thread.start();
        thread2.start();
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
