package com.cashbang.netty.tomcat.bio.http;

import java.io.OutputStream;

/**
 * @Author: huangdejie
 * @Date: 2019/6/21
 */
public class DJResponse {

    private OutputStream outputStream;

    public DJResponse(OutputStream outputStream){
        this.outputStream = outputStream;
    }

    public void write(String s) throws Exception{
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\n")
                .append("Content-Type:text/html;\n")
                .append("\r\n")
                .append(s);
        outputStream.write(sb.toString().getBytes());
    }

}
