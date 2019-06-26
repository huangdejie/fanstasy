package com.cashbang.netty.rpc.consumer.proxy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author: huangdejie
 * @Date: 2019/6/26
 */
public class RpcProxyHandle extends ChannelInboundHandlerAdapter {

    private Object response;

    public Object getResponse(){
        return response;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        response = msg;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("client exception");
    }
}
