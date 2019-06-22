package com.cashbang.netty.rpc.registry;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Author: huangdejie
 * @Date: 2019/6/22
 */
public class RpcRegistry {

    private int port;

    public RpcRegistry(int port){
        this.port = port;
    }

    public void start(){
        //初始化主线程，selector
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //子线程初始化，具体对应客户端的处理逻辑
        EventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap server = new ServerBootstrap();
        server.group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //在netty中，把所有的业务逻辑处理全部归总到一个队列中
                        //队列中包含了具体的处理逻辑，对这些处理逻辑在netty中有一个封装
                        //封装成了一个对象，无所化串行任务队列
                        //Pipline
                        ChannelPipeline pipeline = ch.pipeline();
                        //pipeline就是对业务处理的逻辑
                    }
                });
    }

    public static void main(String[] args) {
        new RpcRegistry(8080).start();
    }

}
