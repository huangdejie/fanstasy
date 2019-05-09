package com.cashbang.designpattern.observer.customize;

import java.util.Vector;

/**
 * 具体定义的被观察者
 * @Author: huangdejie
 * @Date: 2019/3/7
 */
public class Cup implements Observable{

    /**
     * 被观察者维护的一个观察者集合
     */
    private Vector<Observer> observers = new Vector<>();

    private float price;

    public Cup(float price){
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer:observers){
            observer.update(price);
        }
    }
}
