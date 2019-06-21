package com.cashbang;

import com.cashbang.fanstasy.FanstasyApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: huangdejie
 * @Date: 2018/8/23 0023 下午 2:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {FanstasyApplication.class})
public class BaseTest {

    private Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @Test
    public void testAAAA(){
        logger.info("nigha");
    }


}
