package com.cashbang.netty.nio.example;

import com.cashbang.BaseTest;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: huangdejie
 * @Date: 2019/6/27
 */
public class NioReadTest extends BaseTest{



    @Test
    public void read(){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File("D://test.scala"));
            FileChannel fileChannel = fis.getChannel();
            ByteBuffer bf = ByteBuffer.allocate(1024);
            logger.info("第一次----限制:{},容量:{},位置:{}",bf.limit(),bf.capacity(),bf.position());
            int length = -1;
            while ((length=fileChannel.read(bf))!=-1){
                bf.clear();
                logger.info("限制:{},容量:{},位置:{}",bf.limit(),bf.capacity(),bf.position());
                byte[] bytes = bf.array();

                String str = new String(bytes,0,length);
                logger.info("数据:{}",str);
                logger.info("******end*******");
                logger.info("限制:{},容量:{},位置:{}",bf.limit(),bf.capacity(),bf.position());
            }
            fileChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void write(){
        FileOutputStream fos=null;
        try{
            fos = new FileOutputStream("D://write.txt");
            FileChannel channel = fos.getChannel();
//            ByteBuffer bf = Charset.forName("utf-8").encode("hahhahahhahaa略略略");
            ByteBuffer bf = ByteBuffer.allocate(1024);
            logger.info("初始化容量:{},限制:{},位置:{}",bf.capacity(),bf.limit(),bf.position());
            bf.put("jicizhendexiangrangzijishui".getBytes());
            bf.put("this is".getBytes());
            bf.clear();
            logger.info("初始化容量2:{},限制:{},位置:{}",bf.capacity(),bf.limit(),bf.position());
            int length = 0;
            while ((length = channel.write(bf))!=0){
                logger.info("写入长度:{}",length);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testReadAndWrite(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(new File("D://test.scala"));
            FileChannel fisChannel = fis.getChannel();
            fos = new FileOutputStream("D://te.txt");
            ByteBuffer bf = ByteBuffer.allocate(1024);
            int length = -1;
            FileChannel channel = fos.getChannel();
            while ((length = fisChannel.read(bf)) != -1){
                bf.flip();
                int outLength = 0;
                while ((outLength = channel.write(bf))!=0){
                    logger.info("读:{},写:{}",length,outLength);
                }
                bf.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(fis!= null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
