package com.cashbang.io.nio;

/**
 * @Author: huangdejie
 * @Date: 2019/6/11
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        if(args != null && args.length>0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (Exception e){

            }
        }
//        ServerSocket socketServer = null;
//        try {
//            socketServer = new ServerSocket(port);
//            System.out.println("The timeServer is start in port:"+port);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        MultiplexerTimeServer multiplexerTimeServer = new MultiplexerTimeServer(port);
        new Thread(multiplexerTimeServer,"MIO-MultiplexerTimeServer-001").start();
    }

}
