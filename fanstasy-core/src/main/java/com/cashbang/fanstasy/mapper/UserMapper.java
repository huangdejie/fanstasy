package com.cashbang.fanstasy.mapper;

import com.cashbang.fanstasy.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by huangdejie on 2018/8/20 0020.
 */
@Repository
public interface UserMapper {

    public UserEntity login(@Param("loginName") String loginName,@Param("password") String password);

}
