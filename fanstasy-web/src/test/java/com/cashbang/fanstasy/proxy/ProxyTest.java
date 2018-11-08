package com.cashbang.fanstasy.proxy;

import com.cashbang.fanstasy.BaseTest;
import org.junit.Test;

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

}