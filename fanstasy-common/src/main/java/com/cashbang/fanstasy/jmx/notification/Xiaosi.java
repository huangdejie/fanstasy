package com.cashbang.fanstasy.jmx.notification;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

/**
 * @Author: huangdejie
 * @Date: 2018/9/7 0007 上午 11:16
 */
public class Xiaosi extends NotificationBroadcasterSupport implements XiaosiMBean{

    private int seq = 0;

    /**
     * 必须继承NotificationBroadcasterSupport
     */
    @Override
    public void hi(String grettings) {
        Notification n = new Notification(//创建一个信息包
                "xiaosi.hi",//给这个notification起个名称
                this,//由谁发出的notification
                ++seq,//一系列通知的序号
                System.currentTimeMillis(),//发出时间
                grettings//发出的消息文本
        );
        sendNotification(n);
    }
}
