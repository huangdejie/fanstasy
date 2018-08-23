package com.cashbang.fanstasy.service;

import com.cashbang.fanstasy.entity.UserEntity;

import java.util.List;

/**
 * Created by huangdejie on 2018/8/20 0020.
 */
public interface UserService {

    public boolean login(String loginName,String password);

    public List<UserEntity> queryUser();

}
