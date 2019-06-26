package com.cashbang.netty.rpc.provider;

import com.cashbang.netty.rpc.api.IPrcHelloService;

/**
 * @Author: huangdejie
 * @Date: 2019/6/26
 */
public class IPrcHelloServiceImpl implements IPrcHelloService {

    @Override
    public String hello(String name) {
        return "你好啊";
    }
}
