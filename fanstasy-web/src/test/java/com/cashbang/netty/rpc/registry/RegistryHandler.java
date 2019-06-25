package com.cashbang.netty.rpc.registry;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author: huangdejie
 * @Date: 2019/6/25
 */
public class RegistryHandler extends ChannelInboundHandlerAdapter {


    //1、根据包名将所有符合条件的class全部扫描出来，放到一个容器中
    //2、给每一个对应的class起一个唯一的名称作为服务名称，保存到容器中
    //3、当有客户端连接过来之后，获取协议内容InvokerProtocol的对象
    //4、要去注册好的容器中找到符合条件的服务
    //5、通过远程调用Provider，得到返回结果，并回复给客户端


    public RegistryHandler() {
        scannerClass("");
        doRegistry();

    }

    private void doRegistry() {
    }

    public void scannerClass(String packageName){
        this.getClass().getClassLoader().getResource(packageName.replaceAll());
    }

    //有客户端连上时，会回调
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

    }

    //连接发生异常时回调
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }
}
