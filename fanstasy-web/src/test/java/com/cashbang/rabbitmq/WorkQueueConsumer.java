package com.cashbang.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Author: huangdejie
 * @Date: 2019/4/30
 */
public class WorkQueueConsumer {

    private final static String QUEUE_NAME = "task_queue";

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = null;
        Channel channel = null;
        factory.setHost("localhost");
        connection = factory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        DeliverCallback deliverCallback = new DeliverCallback() {
            @Override
            public void handle(String s, Delivery delivery) throws IOException {
                String message = new String(delivery.getBody(),"UTF-8");
                try{
                Thread.sleep(5000);
                }catch (Exception e){

                }

                System.out.println("[x] received '"+message+"'");
                try{
                    doWork(message);
                }catch (Exception e){
                    System.out.println("is error");
                }finally {
                    System.out.println("[x] done");
                }
            }
        };
        boolean autoAck = false;
        channel.basicConsume("task_queue",autoAck,deliverCallback,new CancelCallback() {
            @Override
            public void handle(String s) throws IOException {
            }
        });
    }

    private static void doWork(String task) throws Exception{
        for(char ch:task.toCharArray()){
            if(ch == '.'){
                Thread.sleep(1000);
            }
        }
    }

}
