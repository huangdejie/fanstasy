package com.cashbang.concurrent.gupao.thread_demo;

/**
 * @Author: huangdejie
 * @Date: 2019/7/30
 */
public class DemoMain {

    PrintProcessor printProcessor;

    public DemoMain() {
        SaveProcessor saveProcessor = new SaveProcessor();
        saveProcessor.start();
        printProcessor = new PrintProcessor(saveProcessor);
        printProcessor.start();
    }

    private void doTest(Request request){
        printProcessor.processRequest(request);
    }


    public static void main(String[] args) {
        Request request = new Request();
        request.setName("jhou0");
        new DemoMain().doTest(request);

    }

}
