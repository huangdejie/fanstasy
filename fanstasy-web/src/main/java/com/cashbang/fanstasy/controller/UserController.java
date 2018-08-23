package com.cashbang.fanstasy.controller;

import com.cashbang.fanstasy.entity.UserEntity;
import com.cashbang.fanstasy.request.UserRequest;
import com.cashbang.fanstasy.response.Response;
import com.cashbang.fanstasy.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by huangdejie on 2018/8/20 0020.
 */
@Api(description = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "用户登录",notes = "根据传入的登录名及密码")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Response login(HttpServletRequest httpServletRequest, @RequestBody UserRequest userRequest){
        userService.login(userRequest.getLoginName(),userRequest.getPassword());
        Response response = new Response();
        return response;
    }

    @ApiOperation(value = "获取用户信息",notes = "获取用户信息")
    @RequestMapping(value = "/queryUserInfo",method = RequestMethod.GET)
    public Response queryUserInfo(HttpServletRequest httpServletRequest){
        List<UserEntity> userEntity = userService.queryUser();
        Response response = new Response();
        response.setDetail(userEntity);
        return response;
    }

}
