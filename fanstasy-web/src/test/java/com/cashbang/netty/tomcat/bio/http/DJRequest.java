package com.cashbang.netty.tomcat.bio.http;

import java.io.InputStream;

/**
 * @Author: huangdejie
 * @Date: 2019/6/21
 */
public class DJRequest {

    private String method;

    private String url;

    public DJRequest(InputStream inputStream){
        try{
            String content = "";
            byte[] buff = new byte[1024];
            int len = 0;
            if((len=inputStream.read(buff))>0){
                content = new String(buff,0,len);
            }
            String line = content.split("\\n")[0];
            String[] arr = line.split("\\s");
            this.method = arr[0];
            this.url = arr[1].split("\\?")[0];
        }catch (Exception e){

        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
