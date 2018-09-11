package com.cashbang.fanstasy.jmx.notification;

import com.cashbang.fanstasy.jmx.Hello;

import javax.management.Notification;
import javax.management.NotificationListener;

/**
 * @Author: huangdejie
 * @Date: 2018/9/7 0007 上午 11:21
 */
public class HiListener implements NotificationListener {

    @Override
    public void handleNotification(Notification notification, Object handback) {
        System.out.println("----------HelloListener-Begin------------");
        System.out.println("\ttype = "+ notification.getType());
        System.out.println("\tsource = "+notification.getSource());
        System.out.println("\tseq = "+notification.getSequenceNumber());
        System.out.println("\tsend time = "+notification.getTimeStamp());
        System.out.println("\tmessage="+notification.getMessage());
        System.out.println("----------HelloListener-End------------");

        if (handback != null) {
            if (handback instanceof Hello) {
                Hello hello = (Hello)handback;
                hello.printHello(notification.getMessage());
            }
        }
    }
}
