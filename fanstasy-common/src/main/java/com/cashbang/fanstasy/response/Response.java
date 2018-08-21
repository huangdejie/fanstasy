package com.cashbang.fanstasy.response;

/**
 * Created by huangdejie on 2018/8/20 0020.
 */
public class Response<T> extends Message {

    private T detail;

    public T getDetail() {
        return detail;
    }

    public void setDetail(T detail) {
        this.detail = detail;
    }
}
