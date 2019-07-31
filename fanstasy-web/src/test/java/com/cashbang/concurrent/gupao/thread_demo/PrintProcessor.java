package com.cashbang.concurrent.gupao.thread_demo;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author: huangdejie
 * @Date: 2019/7/30
 */
public class PrintProcessor extends Thread implements RequestProcessor {

    LinkedBlockingQueue<Request> requests = new LinkedBlockingQueue<>();

    private final RequestProcessor nextProcessor;

    public PrintProcessor(RequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    public void run() {
        System.out.println("PrintProcessor启动了。。。");
        while (true){
            try {
                Request request = requests.take();
                System.out.println("print data:"+request.getName());
                nextProcessor.processRequest(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void processRequest(Request request) {
        requests.add(request);
    }
}
