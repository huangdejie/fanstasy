package com.cashbang.fanstasy.response;

/**
 * Created by huangdejie on 2018/8/20 0020.
 */
public class Response<T> extends Message {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
