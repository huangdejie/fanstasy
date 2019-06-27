package com.cashbang.netty.rpc.registry;

import com.cashbang.netty.rpc.protocol.InvokerProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    private List<String> classNames = new ArrayList<>();
    private Map<String,Object> serviceMap = new HashMap<>();

    public RegistryHandler() {
        scannerClass("com.cashbang.netty.rpc.provider");
        doRegistry();

    }

    private void doRegistry() {
        if(classNames.isEmpty()){
            return;
        }
        for(String className:classNames){
            try {
                Class clazz = Class.forName(className);
                Class<?> interClass = clazz.getInterfaces()[0];
                serviceMap.put(interClass.getName(),clazz.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void scannerClass(String packageName){
        URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.","/"));
        File dir = new File(url.getFile());
        for (File file:dir.listFiles()){
            if(file.isDirectory()){
                scannerClass(packageName+"."+file.getName());
                continue;
            }
            classNames.add(packageName+"."+file.getName().replaceAll(".class","").trim());
        }

    }

    //有客户端连上时，会回调
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("接收到客户端的请求...");
        Object result = null;
        InvokerProtocol request = (InvokerProtocol) msg;
        if(serviceMap.containsKey(request.getClassName())){
           Object obj = serviceMap.get(request.getClassName());
            Method method = obj.getClass().getMethod(request.getMethodName(),request.getParames());
            result = method.invoke(obj,request.getValues());
        }
        ctx.write(result);
        ctx.flush();
        ctx.close();
    }

    //连接发生异常时回调
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
