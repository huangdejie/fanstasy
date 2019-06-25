package com.cashbang.netty.rpc.registry;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

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
        try {
            //初始化主线程，selector
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            //子线程初始化，具体对应客户端的处理逻辑
            EventLoopGroup workGroup = new NioEventLoopGroup();
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //在netty中，把所有的业务逻辑处理全部归总到一个队列中
                            //队列中包含了具体的处理逻辑，对这些处理逻辑在netty中有一个封装
                            //封装成了一个对象，无锁化串行任务队列
                            //Pipline
                            ChannelPipeline pipeline = ch.pipeline();
                            //pipeline就是对业务处理的逻辑
                            //对于自定义协议的内容进行编解码
                            pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                            //自定义编码器
                            pipeline.addLast(new LengthFieldPrepender(4));
                            //实参处理
                            pipeline.addLast("encoder", new ObjectEncoder());
                            pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));

                            //前面的编解码，就是完成对数据的解析
                            //最后一步，执行逻辑
                            //1、注册，给每一个对象起一个名字，对外提供服务的名称
                            //2、服务位置做一个登记
                            pipeline.addLast(new RegistryHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)//128最大selectionkey的数量
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            //正式启动服务相当于用一个死循环开始轮询
            ChannelFuture future = server.bind(this.port).sync();
            System.out.println("RPC Registry start listen at："+this.port);
            future.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new RpcRegistry(8080).start();
    }

}
