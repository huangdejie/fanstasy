package com.cashbang.fanstasy.controller;

import com.cashbang.fanstasy.auth.TokenComponent;
import com.cashbang.fanstasy.request.UserRequest;
import com.cashbang.fanstasy.response.Response;
import com.cashbang.fanstasy.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by huangdejie on 2018/8/20 0020.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TokenComponent tokenComponent;

    @Resource
    private UserService userService;

    @ApiOperation(value = "用户登录",notes = "根据传入的登录名及密码")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Response login(HttpServletRequest httpServletRequest, @RequestBody UserRequest userRequest){
        tokenComponent.checkSyncAuth(httpServletRequest);
        userService.login(userRequest.getLoginName(),userRequest.getPassword());
        Response response = new Response();
        return response;
    }

    @ApiOperation(value = "用户登录",notes = "根据传入的登录名及密码")
    @RequestMapping(value = "/logins",method = RequestMethod.GET)
    public Response login(HttpServletRequest httpServletRequest, @RequestParam("loginName") String loginName, @RequestParam("password") String password){
        Response response = tokenComponent.checkSyncAuth(httpServletRequest);
        if(response != null){
            return response;
        }
        userService.login(loginName,password);
        Response response = new Response();
        return response;
    }

}
