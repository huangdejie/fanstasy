package com.cashbang.concurrent.eassy;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * @Author: huangdejie
 * @Date: 2019/5/5
 */
public class ObjPool<T,R> {

    final List<T> pool;

    final Semaphore semaphore;

   ObjPool(int size,T t){
        pool = new Vector<T>();
       for(int i = 0;i<size;i++){
           pool.add(t);
       }
       semaphore = new Semaphore(size);
    }

    R exec(Function<T,R> function) throws InterruptedException{
        T t = null;
        semaphore.acquire();
        try{
            t = pool.remove(0);
            return function.apply(t);
        }finally {
            pool.add(t);
            semaphore.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ObjPool<Integer,Integer> pool = new ObjPool<Integer, Integer>(10,2);
        pool.exec(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                System.out.println(integer);
                return integer;
            }
        });
    }

}
