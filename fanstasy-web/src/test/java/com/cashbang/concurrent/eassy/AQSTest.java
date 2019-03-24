package com.cashbang.concurrent.eassy;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Author: huangdejie
 * @Date: 2019/2/22
 */
public class AQSTest extends AbstractQueuedSynchronizer {

    protected AQSTest() {
        super();
    }

    @Override
    protected boolean tryAcquire(int arg) {
        return super.tryAcquire(arg);
    }

    @Override
    protected boolean tryRelease(int arg) {
        return super.tryRelease(arg);
    }

    @Override
    protected int tryAcquireShared(int arg) {
        return super.tryAcquireShared(arg);
    }

    @Override
    protected boolean tryReleaseShared(int arg) {
        return super.tryReleaseShared(arg);
    }

    @Override
    protected boolean isHeldExclusively() {
        return super.isHeldExclusively();
    }

}
