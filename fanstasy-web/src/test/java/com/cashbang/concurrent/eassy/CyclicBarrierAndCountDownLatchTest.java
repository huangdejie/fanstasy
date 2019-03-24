package com.cashbang.concurrent.eassy;

import com.cashbang.BaseTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Author: huangdejie
 * @Date: 2019/2/19
 */
public class CyclicBarrierAndCountDownLatchTest extends BaseTest {

    @Test
    public void testCyclicBarrier(){
        ExecutorService pool = Executors.newCachedThreadPool();
        int size = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(size,()->{
            System.out.println(size+"位运动员都准备好了，可以起跑");
            pool.shutdown();
        });
        for(int i= 0; i<size; i++){
            int index = i;
            pool.submit(()->{
                try {
                    TimeUnit.SECONDS.sleep(index);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("第"+index+"位运动员准备好了");
                try {
                    cyclicBarrier.await();
                }catch (InterruptedException | BrokenBarrierException e){
                    e.printStackTrace();
                }
            });
        }
        System.out.println("-------");
    }

    @Test
    public void testCountDownLatch(){
        ExecutorService pool = Executors.newCachedThreadPool();
        int size = 3;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for(int i=0;i<size;i++){
            int index = i;
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(index);
                        countDownLatch.countDown();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println("第"+index+"位运动员准备好了");
                }
            });
        }
        System.out.println("我就要插播一条广告");
        try {
            countDownLatch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        pool.shutdown();
        System.out.println(size+"位运动员都准备好了，可以起跑");

    }

    @Test
    public void testMapEntry(){
        Map<String,String> maps = new HashMap<>();
        maps.put("hello","hello");
        maps.put("world","world");
        for(Map.Entry<String,String> entry: maps.entrySet()){
            System.out.println("******************"+entry.getKey()+"\t"+entry.getValue());
        }
    }

}
