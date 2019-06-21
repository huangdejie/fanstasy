package com.cashbang.concurrent.stamp;

import java.util.concurrent.locks.StampedLock;

/**
 * @Author: huangdejie
 * @Date: 2019/5/14
 */
public class StampedLockModel {

    final StampedLock s1 = new StampedLock();

    public void readModel(){
        long stamp = s1.tryOptimisticRead();

    }

}
