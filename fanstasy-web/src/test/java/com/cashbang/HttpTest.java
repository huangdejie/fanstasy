package com.cashbang;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: huangdejie
 * @Date: 2019/6/18
 */
public class HttpTest extends BaseTest {

    @Test
    public void testHttlClient(){
        HttpClient httpClient = new DefaultHttpClient();
//        Htt
        String url = "http://localhost:9090/docker/user/login";
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-type","application/json; charset=utf-8");
        httpPost.setHeader("Accept", "application/json");
        Map<String,Object> map = new HashMap<>();
        map.put("memberId","zhangsan");
        Gson gson = new Gson();
        String json = gson.toJson(map);
        httpPost.setEntity(new StringEntity(json, Charset.forName("UTF-8")));
        try {
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            InputStream inputStream = httpEntity.getContent();
            String body = IOUtils.toString(inputStream,"UTF-8");
            System.out.println(body);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
