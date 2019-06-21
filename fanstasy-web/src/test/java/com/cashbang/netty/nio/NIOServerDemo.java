package com.cashbang.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: huangdejie
 * @Date: 2019/6/19
 */
public class NIOServerDemo {

    private int port = 8080;

    //轮询器 selector
    private Selector selector;
    //缓冲区 Buffer
    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    public NIOServerDemo(int port) {
        //初始化轮询器
        this.port = port;
        try {
            ServerSocketChannel server = ServerSocketChannel.open();
            //IP+port
            server.bind(new InetSocketAddress(port));
            //NIO为了兼容BIO，NIO模型默认采用阻塞式
            server.configureBlocking(false);

            //轮询器准备就绪、
            selector = Selector.open();
            server.register(selector, SelectionKey.OP_ACCEPT);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void process(SelectionKey selectionKey) throws Exception{
        //针对每一种状态给一个反应
        if(selectionKey.isAcceptable()){
            ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
            SocketChannel channel = server.accept();
            channel.configureBlocking(false);
            selectionKey = channel.register(selector,SelectionKey.OP_READ);
        }else if(selectionKey.isReadable()){
            //selectionKey.channel 从多路复用器中拿到客户端的引用
            SocketChannel channel = (SocketChannel) selectionKey.channel();
            int len = channel.read(buffer);
            if(len > 0){
                buffer.flip();
                String content = new String(buffer.array(),0,len);
                selectionKey = channel.register(selector,SelectionKey.OP_WRITE);
                //
                selectionKey.attach(content);
                System.out.println("读取内容"+content);
            }
        }else if(selectionKey.isWritable()){
            SocketChannel channel = (SocketChannel) selectionKey.channel();
            String content = (String) selectionKey.attachment();
            channel.write(ByteBuffer.wrap(("输出："+content).getBytes()));
            channel.close();
        }
    }

    public void listen(){
        System.out.println("Listen on"+this.port+"。");
        //轮询
        try {
            while (true) {
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                //不断的迭代——》轮询
                Iterator<SelectionKey> iterator = keys.iterator();
                //同步体现在这里，因为每次只能那一个key，每次只能处理一种状态
                while (iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    //每个key代表一种状态
                    process(selectionKey);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new NIOServerDemo(8080).listen();
    }
}
