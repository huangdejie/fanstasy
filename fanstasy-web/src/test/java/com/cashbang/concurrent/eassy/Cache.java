package com.cashbang.concurrent.eassy;

import org.apache.commons.collections.map.HashedMap;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: huangdejie
 * @Date: 2019/5/5
 */
public class Cache<K,V> {

    final Map<K,V> map = new HashedMap();

    final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    final Lock readLock = readWriteLock.readLock();

    final Lock writeLock = readWriteLock.writeLock();

    V get(K k){
        readLock.lock();
        try{
            return map.get(k);
        }finally {
            readLock.unlock();
        }
    }

    V put(K key,V v){
        writeLock.lock();
        try{
            return map.put(key,v);
        }finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        Cache<String,Integer> cache = new Cache<>();

    }

}
