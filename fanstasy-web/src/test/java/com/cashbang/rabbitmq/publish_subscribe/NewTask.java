package com.cashbang.rabbitmq.publish_subscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Scanner;

/**
 * @Author: huangdejie
 * @Date: 2019/5/5
 */
public class NewTask {

    private final static String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("logs","fanout");
//        String queueName = channel.queueDeclare().getQueue();
//        channel.queueBind(queueName,"logs","");
        Scanner scanner = new Scanner(System.in);
        while (true){
            String message = scanner.next();
            channel.basicPublish("logs","", null,message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '"+message+"'");
        }
    }

}
