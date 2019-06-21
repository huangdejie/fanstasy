package com.cashbang.netty.tomcat.bio.servlet;

import com.cashbang.netty.tomcat.bio.http.DJRequest;
import com.cashbang.netty.tomcat.bio.http.DJResponse;
import com.cashbang.netty.tomcat.bio.http.DJServlet;

/**
 * @Author: huangdejie
 * @Date: 2019/6/21
 */
public class FirstServlet extends DJServlet {

    @Override
    protected void doGet(DJRequest request, DJResponse response) throws Exception{
        this.doPost(request,response);
    }

    @Override
    protected void doPost(DJRequest request, DJResponse response) throws Exception{
        response.write("This is First Servlet");
    }
}
