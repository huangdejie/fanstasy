package com.cashbang.rabbitmq.work_queue;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Author: huangdejie
 * @Date: 2019/5/5
 */
public class Consumer {

    private final static String QUEUE_NAME = "persistence_queue";

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        channel.basicQos(1);
        DeliverCallback deliverCallback = new DeliverCallback() {
            @Override
            public void handle(String s, Delivery delivery) throws IOException {
                String message = new String(delivery.getBody(),"UTF-8");
                System.out.println("received message "+message);
                try {
                    doWork(message);
                }finally {
                    System.out.println("consumer have done");
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
                }
            }
        };
        channel.basicConsume(QUEUE_NAME, false, deliverCallback, consumerTag -> { });
    }
    private static void doWork(String task) {
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException _ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

}
