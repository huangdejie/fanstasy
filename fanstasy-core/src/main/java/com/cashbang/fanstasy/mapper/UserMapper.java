package com.cashbang.fanstasy.mapper;

import com.cashbang.fanstasy.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by huangdejie on 2018/8/20 0020.
 */
@Repository
public interface UserMapper {

    UserEntity login(@Param("loginName") String loginName,@Param("password") String password);

    List<UserEntity> queryUser();
}
