package com.cashbang.fanstasy.service;

import com.cashbang.fanstasy.entity.UserEntity;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by huangdejie on 2018/8/21 0021.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ConfigurationProperties("classpath*:application.properties,classpath*:application-dev.properties")
@MapperScan("com.cashbang.fanstasy.mapper")
@SpringBootTest
public class UserTest {

    @Resource
    private UserService userService;

    @Test
    public void testUser(){
        userService.login("nihao","nihoa");
    }

    @Test
    public void testJson(){
        UserEntity userEntity = new UserEntity();
        userEntity.setLoginName("nihao");
        userEntity.setPassword("nihao123");
        Gson gson = new Gson();
        System.out.println(gson.toJson(userEntity));
    }


}
