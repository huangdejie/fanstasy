package com.fanstasy.thread;

/**
 * @Author: huangdejie
 * @Date: 2018/9/11 0011 上午 10:55
 */
public class ALogin extends Thread {

    @Override
    public void run() {
        super.run();
        LoginServlet.doPost("a","aa");
    }
}
