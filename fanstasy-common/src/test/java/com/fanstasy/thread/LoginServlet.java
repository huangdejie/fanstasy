package com.fanstasy.thread;

/**
 * @Author: huangdejie
 * @Date: 2018/9/11 0011 上午 10:51
 */
public class LoginServlet {

    private static String userNameRef;
    private static String passwordRef;

    synchronized public static void doPost(String username,String password){
        userNameRef = username;
        try{
            System.out.println(Thread.currentThread().getName());
            if(userNameRef.equals("a")){
                Thread.sleep(5000);
            }
            passwordRef = password;
            System.out.println("userName="+userNameRef+"********password="+passwordRef);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
