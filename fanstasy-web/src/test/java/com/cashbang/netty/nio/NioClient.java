package com.cashbang.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @Author: huangdejie
 * @Date: 2019/6/27
 */
public class NioClient {

    private final InetSocketAddress serverAddress = new InetSocketAddress("localhost",8080);

    private Selector selector;

    private SocketChannel client;

    private String nickName = "";

    private Charset charset = Charset.forName("UTF-8");

    private static String USER_EXIST = "系统提示:该昵称已存在，请换一个";
    private static String USER_CONTENT_SPLIT="#@#";

    public NioClient() throws IOException {
        selector = Selector.open();
        client = SocketChannel.open(serverAddress);
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
    }

    public static void main(String[] args) throws IOException {
        new NioClient().session();
    }

    public void session(){
        new Reader().start();
        new Write().start();
    }

    private class Write extends Thread{
        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                if("".equals(line)) {
                    continue;
                }
                if("".equals(nickName)){
                    nickName = line;
                    line = nickName+USER_CONTENT_SPLIT;
                }else{
                    line = nickName + USER_CONTENT_SPLIT+line;
                }
                try {
                    client.write(charset.encode(line));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            scanner.close();
        }
    }

    private class Reader extends Thread{
        @Override
        public void run() {
            while (true){
                try {
                    int readyChannels = selector.select();
                    if(readyChannels ==0 ){
                        continue;
                    }
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
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

        private void process(SelectionKey key) throws IOException {
            if(key.isReadable()){
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer bf = ByteBuffer.allocate(1024);
                String content = "";
                while (sc.read(bf)>0){
                    bf.flip();
                    content += charset.decode(bf);
                }
                if(USER_EXIST.equals(content)){
                    nickName = "";
                }
                System.out.println(content);
                key.interestOps(SelectionKey.OP_READ);
            }
        }
    }

}
