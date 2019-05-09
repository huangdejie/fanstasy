package com.cashbang.rabbitmq;

import com.cashbang.BaseTest;
import com.rabbitmq.client.*;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Author: huangdejie
 * @Date: 2019/4/30
 */
public class ProducerTest extends BaseTest {

    private final static String QUEUE_NAME = "hello";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Connection connection = null;

    private Channel channel = null;

    @Before
    public void preGetConnection(){
        ConnectionFactory factory = new ConnectionFactory();
        try {
            factory.setHost("localhost");
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        }catch (Exception e){
            logger.error("错误：{}",e);
        }
    }

    @Test
    public void testProducer() throws Exception{
        String message = "hello world";
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        logger.info("信息发送完毕");
    }

    @Test
    public void testConsumer() throws Exception{
        DeliverCallback deliverCallback = new DeliverCallback() {
            @Override
            public void handle(String s, Delivery delivery) throws IOException {
                String message = new String(delivery.getBody(),"UTF-8");
                logger.info("回调数据:{}",message);
            }
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, new CancelCallback() {
            @Override
            public void handle(String s) throws IOException {
                logger.info("啥啥啥:{}",s);
            }
        });
    }
}
