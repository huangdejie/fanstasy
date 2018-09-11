package com.fanstasy.thread;

/**
 * @Author: huangdejie
 * @Date: 2018/9/11 0011 上午 10:04
 */
public class Test {

//    public static void main(String[] args) {
//        Thread1 thread1 = new Thread1("thread1");
//        Thread1 thread2 = new Thread1("thread2");
//        Thread1 thread3 = new Thread1("thread3");
//        thread1.start();
//        thread2.start();
//        thread3.start();
//    }

    @org.junit.Test
    public void test1(){
        Thread1 thread1 = new Thread1();
        Thread threadA = new Thread(thread1,"A");
        Thread threadB = new Thread(thread1,"B");
        Thread threadC = new Thread(thread1,"C");
        Thread threadD = new Thread(thread1,"D");
        Thread threadE = new Thread(thread1,"E");
        Thread threadF = new Thread(thread1,"F");
        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
        threadE.start();
        threadF.start();
    }

    public static void main(String[] args) {
        ALogin aLogin = new ALogin();
        BLogin bLogin = new BLogin();
        aLogin.start();
        bLogin.start();
    }
}
