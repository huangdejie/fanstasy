package com.cashbang.concurrent.eassy;

/**
 * @Author: huangdejie
 * @Date: 2019/2/19
 */
public class SonCloneable implements Cloneable {

    public static void main(String[] args) {
        SonCloneable sonCloneable = new SonCloneable();
        System.out.println("最初始的:"+sonCloneable);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                ThreadLocal<SonCloneable> threadLocal = new ThreadLocal<>();
                System.out.println("new 之后:"+threadLocal);
                threadLocal.set(sonCloneable);
                System.out.println("设置本地变量之后:"+threadLocal.get());
                threadLocal.remove();
                try {
                    threadLocal.set((SonCloneable)sonCloneable.clone());
                    System.out.println("移除本地变量之后重新克隆的:"+threadLocal.get());
                }catch (CloneNotSupportedException e){
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

}
