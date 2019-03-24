package com.cashbang.fanstasy.proxy;

import com.cashbang.BaseTest;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @Author: huangdejie
 * @Date: 2018/11/8
 */
public class ProxyTest extends BaseTest {

    @Test
    public void testProxy(){
        UserService userService = new UserServiceImpl();
        System.out.println(userService.getClass());
        UserService proxy = (UserService) new ProxyFactory(userService).getProxyInstance();
        System.out.println(proxy.getClass());
        proxy.eatFood();
    }

    @Test
    public void testSocket() throws IOException{
        ServerSocket serverSocket = new ServerSocket(8080);
        serverSocket.accept();

    }

}
