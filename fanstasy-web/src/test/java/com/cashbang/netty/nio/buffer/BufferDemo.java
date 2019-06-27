package com.cashbang.netty.nio.buffer;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: huangdejie
 * @Date: 2019/6/27
 */
public class BufferDemo {


    public static void main(String[] args) throws Exception{
        FileInputStream fis = new FileInputStream(new File("D://write.txt"));
        FileChannel fileChannel = fis.getChannel();
        //分配一个10个大小缓冲区，就是分配一个10个大小的byte数组
        ByteBuffer buffer = ByteBuffer.allocate(10);
        output("初始化",buffer);

        fileChannel.read(buffer);
        output("调用read()",buffer);

        buffer.flip();
        output("调用flip()",buffer);

        while (buffer.remaining()>0){
            byte b = buffer.get();
            System.out.println("拉拉:"+(char)b);
        }
        output("调用get()",buffer);


        buffer.clear();
        output("调用clear",buffer);
        fis.close();
    }

    public static void output(String step,ByteBuffer buffer){
        System.out.println(step+" : ");
        System.out.print("capacity："+buffer.capacity()+", ");
        System.out.print("position："+buffer.position()+", ");
        System.out.print("limit："+buffer.limit());
        System.out.println();
    }

}
