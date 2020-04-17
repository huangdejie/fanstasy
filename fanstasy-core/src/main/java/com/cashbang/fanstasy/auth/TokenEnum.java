package com.cashbang.fanstasy.auth;

/**
 * @Author: huangdj
 * @Date: 2020/4/17
 */
public enum TokenEnum {

    SERVER_ERROR(2000,"服务器通讯失败!"),
    APP_ID_IS_NOT_EXIST(2001,"appid不存在!"),
    TOKEN_IS_EXPIRED(2002,"token已过期!"),
    SIGN_IS_ERROR(2003,"签名错误"),
    SIGN_IS_EMPTY(2004,"签名为空!");

    private int code;
    private String desc;

    private TokenEnum(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
