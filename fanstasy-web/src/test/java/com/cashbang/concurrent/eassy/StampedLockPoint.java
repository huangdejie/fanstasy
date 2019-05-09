package com.cashbang.concurrent.eassy;

import java.util.concurrent.locks.StampedLock;

/**
 * @Author: huangdejie
 * @Date: 2019/5/9
 */
public class StampedLockPoint {

    private int x;

    private int y;

    final StampedLock stampedLock = new StampedLock();

    public double distanceFromOrigin(){
        //乐观读
        long stamp = stampedLock.tryOptimisticRead();
        //读入局部变量，在读的过程中x、y可能会被改变
        int curX = x;
        int curY = y;
        //判断执行读操作期间是否存在写操作,若存在则返回false
        if(!stampedLock.validate(stamp)){
            //升级为悲观锁
            stamp = stampedLock.readLock();
            try{
                curX = x;
                curY = y;
            }finally {
                stampedLock.unlockRead(stamp);
            }
        }
        return Math.sqrt(curX*curX+curY*curY);
    }

}
