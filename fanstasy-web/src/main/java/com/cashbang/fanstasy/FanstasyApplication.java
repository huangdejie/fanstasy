package com.cashbang.fanstasy;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by huangdejie on 2018/8/20 0020.
 */
@SpringBootApplication
@MapperScan("com.cashbang.fanstasy.mapper")
public class FanstasyApplication {

    private static Logger logger = LoggerFactory.getLogger(FanstasyApplication.class);

    public static void main(String[] args) {
        logger.info("项目启动了:{}","haha");
        SpringApplication.run(FanstasyApplication.class,args);
    }

}
