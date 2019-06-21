package com.cashbang.netty.tomcat.bio.http;

/**
 * @Author: huangdejie
 * @Date: 2019/6/21
 */
public abstract class DJServlet {

    public void service(DJRequest request,DJResponse response) throws Exception{
        if("GET".equalsIgnoreCase(request.getMethod())){
            doGet(request,response);
        }else {
            doPost(request,response);
        }
    }

    protected abstract void doGet(DJRequest request, DJResponse response) throws Exception;

    protected abstract void doPost(DJRequest request, DJResponse response) throws Exception;

}
