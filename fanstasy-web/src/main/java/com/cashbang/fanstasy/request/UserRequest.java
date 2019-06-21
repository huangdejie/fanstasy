package com.cashbang.fanstasy.request;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by huangdejie on 2018/8/20 0020.
 */
public class UserRequest {

    @ApiModelProperty("用户姓名")
    private String userName;
    @ApiModelProperty("登录名")
    private String loginName;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("创建时间")
    private Date createDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
