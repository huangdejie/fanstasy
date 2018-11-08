package com.cashbang.fanstasy;

/**
 * @Author: huangdejie
 * @Date: 2018/11/8
 */
public class MyRunnable implements Runnable{

    public static Integer num = new Integer(0);

    @Override
    public void run() {
        while (true){
            synchronized (num){
                if(num<100){
                    //num++ ——》Integer.valueOf(num.intValue()+1);
                    //而valueOf每次都会new出来一个新的对象,synchronized就起不到想要的作用了
                    num++;
                    System.out.println(Thread.currentThread().getName()+"**********num="+num);
                }else{
                    break;
                }
            }
        }
    }


}
