package com.cashbang.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: huangdejie
 * @Date: 2019/6/27
 */
public class NioServer {

    private int port = 8080;

    private Selector selector;

    private Charset charset = Charset.forName("UTF-8");

    private static final String USER_CONTENT_SPILIT = "#@#";
    private final String USER_EXIST = "该昵称已存在";


    private Set<String> users = new HashSet<String>();

    public NioServer(int port) {
        this.port = port;
        try {
            ServerSocketChannel socketChannel = ServerSocketChannel.open();
            socketChannel.bind(new InetSocketAddress(this.port));
            socketChannel.configureBlocking(false);
            selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void listen() throws Exception{
        while (true){
            int wait = selector.select();
            if(wait == 0){
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator =   selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectioKey = iterator.next();
                iterator.remove();
                process(selectioKey);
            }

        }
    }

    private void process(SelectionKey key) throws IOException {
        if(key.isAcceptable()){
            ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
            SocketChannel channel = serverChannel.accept();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ);
            key.interestOps(SelectionKey.OP_ACCEPT);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("hello,请输入昵称:".getBytes());
            channel.write(buffer);
        }else if(key.isReadable()){
            SocketChannel socketChannel = (SocketChannel) key.channel();
            int length = 0;
            StringBuilder content = new StringBuilder();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while ((length = socketChannel.read(byteBuffer))>0){
                byteBuffer.flip();
                content.append(charset.decode(byteBuffer));
            }
            key.interestOps(SelectionKey.OP_READ);
            if(content.length()>0){
                String[] array = content.toString().split(USER_CONTENT_SPILIT);
                if(array != null && array.length == 1){
                    String nickName = array[0];
                    if(users.contains(nickName)){
                       socketChannel.write(charset.encode(USER_EXIST));
                    }else{
                        users.add(nickName);
                        int onlineCount = onlineCount();
                        String message = "欢迎"+nickName+"进入聊天室！当前在线人数："+onlineCount;
                        broadCast(null,message);
                    }
                }else if(array != null && array.length>1){
                    String nickName = array[0];
                    String message = content.substring(nickName.length()+USER_CONTENT_SPILIT.length());
                    message = nickName + ":"+message;
                    if(users.contains(nickName)){
                        broadCast(socketChannel,message);
                    }
                }
            }

        }
    }

    private void broadCast(SocketChannel socketChannel, String message) throws IOException {
        for(SelectionKey key:selector.keys()){
            Channel targetChannel = key.channel();
            if(targetChannel instanceof SocketChannel &&
                    targetChannel != socketChannel){
                SocketChannel target = (SocketChannel) targetChannel;
                target.write(charset.encode(message));
            }
        }
    }

    private int onlineCount() {
        int res = 0;
        for(SelectionKey key:selector.keys()){
            Channel target = key.channel();
            if(target instanceof SocketChannel){
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        new NioServer(8080).listen();
    }

}
