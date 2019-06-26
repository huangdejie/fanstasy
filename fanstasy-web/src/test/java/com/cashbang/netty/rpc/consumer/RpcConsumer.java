package com.cashbang.netty.rpc.consumer;

import com.cashbang.netty.rpc.api.IPrcHelloService;
import com.cashbang.netty.rpc.consumer.proxy.RpcProxy;

/**
 * @Author: huangdejie
 * @Date: 2019/6/26
 */
public class RpcConsumer {

    public static void main(String[] args) {
        IPrcHelloService iPrcHelloService = RpcProxy.create(IPrcHelloService.class);
        String result = iPrcHelloService.hello("ni");
        System.out.println(result);
    }

}
