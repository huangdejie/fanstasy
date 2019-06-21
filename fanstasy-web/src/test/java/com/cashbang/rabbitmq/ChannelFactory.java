package com.cashbang.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Author: huangdejie
 * @Date: 2019/4/30
 */
public class ChannelFactory {

    public static Channel getChannel(String queueName) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = null;
        Channel channel = null;
        factory.setHost("localhost");
        connection = factory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare(queueName,false,false,false,null);
        return channel;
    }

}
