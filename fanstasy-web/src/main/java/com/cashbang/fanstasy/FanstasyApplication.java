package com.cashbang.fanstasy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by huangdejie on 2018/8/20 0020.
 */
@SpringBootApplication
@MapperScan("com.cashbang.fanstasy.mapper")
public class FanstasyApplication {

    public static void main(String[] args) {
        SpringApplication.run(FanstasyApplication.class,args);
    }

}
