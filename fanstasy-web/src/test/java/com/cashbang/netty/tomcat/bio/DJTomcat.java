package com.cashbang.netty.tomcat.bio;

import com.cashbang.netty.tomcat.bio.http.DJRequest;
import com.cashbang.netty.tomcat.bio.http.DJResponse;
import com.cashbang.netty.tomcat.bio.http.DJServlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: huangdejie
 * @Date: 2019/6/21
 */
public class DJTomcat {

    //J2EE标准
    //Servlet
    //Request
    //Response

    //1.配置好启动端口(8080 ServerSocket)
    //2.配置web.xml文件
    //  servlet-name
    //  servlet-class
    //  url-pattern
    //3.读取配置，url-pattern和servlet建立映射关系
    //  Map servletMapping
    //4.Http请求,发送的数据就是字符串，有一定的规定(HTTP协议)
    //5.从协议中拿到url，把相应的servlet利用反射进行实例化
    //6.调用实例化对象的service()方法,执行具体的逻辑doGet/doPost
    // Request(InputStream)/Response(OutputStream的封装)

    private int port = 8080;
    private ServerSocket server;
    private Map<String,DJServlet> servletMap = new HashMap<>();
    private Properties webProperties = new Properties();

    private void init(){
        //加载web.properties文件,同时初始化servletMapping对象
        try {
            String WEB_INF = this.getClass().getResource("/").getPath();
            FileInputStream fis = new FileInputStream(WEB_INF+"web.properties");
            webProperties.load(fis);
            for(Object k:webProperties.keySet()){
                String key = k.toString();
                if(key.endsWith(".url")){
                    String servletName = key.replaceAll("\\.url$","");
                    String url = webProperties.getProperty(key);
                    String className = webProperties.getProperty(servletName+".className");
                    DJServlet obj = (DJServlet) Class.forName(className).newInstance();
                    servletMap.put(url,obj);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void start(){
        init();
        try {
            server = new ServerSocket(this.port);
            System.out.println("DJ Tomcat已启动,监听的端口是:"+this.port);
            while (true){
                Socket socket = server.accept();
                process(socket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void process(Socket socket) throws Exception{
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        DJRequest request = new DJRequest(is);
        DJResponse response =new DJResponse(os);
        String url = request.getUrl();
        if(servletMap.containsKey(url)){
            servletMap.get(url).service(request,response);
        }else {
            response.write("404 - NOT Found");
        }
        os.flush();
        os.close();
        is.close();
        socket.close();
    }

    public static void main(String[] args) {
        new DJTomcat().start();
    }
}
