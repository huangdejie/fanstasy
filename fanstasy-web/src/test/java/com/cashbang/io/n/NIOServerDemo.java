package com.cashbang.io.n;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: huangdejie
 * @Date: 2019/7/22
 */
public class NIOServerDemo {

    private int port = 8080;

    private Selector selector;

    public NIOServerDemo(int port){
        this.port = port;
        try {
            ServerSocketChannel server = ServerSocketChannel.open();
            server.bind(new InetSocketAddress(port));
            server.configureBlocking(false);
            selector = Selector.open();
            server.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen(){
        System.out.println("服务已启动，监听端口:"+this.port);
        while (true){
            try {
                selector.select();
                Set<SelectionKey> keySet= selector.selectedKeys();
                Iterator<SelectionKey> iterator  = keySet.iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    process(key);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
    }

    private void process(SelectionKey key) {
        if(key.isAcceptable()){

        }
    }

}
