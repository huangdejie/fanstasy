package com.cashbang.concurrent.gupao.thread_demo;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author: huangdejie
 * @Date: 2019/7/30
 */
public class SaveProcessor extends Thread implements RequestProcessor{

    private LinkedBlockingQueue<Request> requests = new LinkedBlockingQueue<>();


    @Override
    public void processRequest(Request request) {
        requests.add(request);
    }

    @Override
    public void run() {
        System.out.println("SaveProcessor 启动了。。。");
        while (true){
            try {
                Request request = requests.take();
                System.out.println("begin save request info:"+request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
