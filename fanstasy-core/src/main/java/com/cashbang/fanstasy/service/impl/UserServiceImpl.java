package com.cashbang.fanstasy.service.impl;

import com.cashbang.fanstasy.entity.UserEntity;
import com.cashbang.fanstasy.mapper.UserMapper;
import com.cashbang.fanstasy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by huangdejie on 2018/8/20 0020.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean login(String loginName, String password) {
        UserEntity userEntity = userMapper.login(loginName,password);
        if(userEntity == null){
            return false;
        }
        return true;
    }

    @Override
    public List<UserEntity> queryUser() {
        return userMapper.queryUser();
    }
}
