package com.cashbang.fanstasy.zkdemo;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: huangdejie
 * @Date: 2018/8/30 0030 下午 2:25
 */
public class ZookeeperConnection {

    private ZooKeeper zoo;

    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    /**
     * 创建zookeeper对象
     * @param host
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ZooKeeper connect(String host) throws IOException,InterruptedException{
        zoo = new ZooKeeper(host, 500, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if(watchedEvent.getState() == Event.KeeperState.SyncConnected){
                    countDownLatch.countDown();
                }
            }
        });
        countDownLatch.await();
        return zoo;
    }

    public void close()throws InterruptedException{
        zoo.close();
    }

}
