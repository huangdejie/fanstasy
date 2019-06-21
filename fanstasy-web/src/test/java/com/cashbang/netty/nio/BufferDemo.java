package com.cashbang.netty.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: huangdejie
 * @Date: 2019/6/20
 */
public class BufferDemo {

    public static void main(String[] args)  throws Exception{
        FileInputStream fileInputStream = new FileInputStream("D://test.txt");
        //创建 文件的操作管道
        FileChannel fileChannel = fileInputStream.getChannel();
        //分配一个10个大小的缓冲区，说白了就是分配一个10个大小的byte数组
        ByteBuffer buffer = ByteBuffer.allocate(10);
        output("初始化",buffer);
        fileChannel.read(buffer);
    }

    private static void output(String step,ByteBuffer buffer){

    }


}
