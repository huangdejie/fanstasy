package com.cashbang.designpattern.observer.customize;

/**
 * @Author: huangdejie
 * @Date: 2019/3/7
 */
public interface Observable {

    /**
     * 注册一个观察者
     * @param observer
     */
    void registerObserver(Observer observer);

    /**
     * 移除一个观察者
     * @param observer
     */
    void removeObserver(Observer observer);

    /**
     * 通知所有观察者更新信息
     */
    void notifyObservers();

}
