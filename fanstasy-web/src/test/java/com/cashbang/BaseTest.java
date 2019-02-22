package com.cashbang;

import com.cashbang.fanstasy.FanstasyApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: huangdejie
 * @Date: 2018/8/23 0023 下午 2:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {FanstasyApplication.class})
public class BaseTest {

    @Test
    public void testAAAA(){
        System.out.println("你好");
    }


}
