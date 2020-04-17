package com.cashbang.fanstasy.response;

import java.io.Serializable;

/**
 * Created by huangdejie on 2018/8/20 0020.
 */
public class Message implements Serializable{

    private static final long serialVersionUID = -6612115585369506873L;
    private String desc;
    private int code;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
