package com.cashbang.fanstasy.auth;

import com.cashbang.fanstasy.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author: huangdj
 * @Date: 2020/4/17
 */
@Component
public class TokenComponent {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 验签
     * @param requset
     * @return
     */
    public Response checkSyncAuth(HttpServletRequest requset) {
        String sign = requset.getParameter("sign");
        try {
            if (StringUtils.isEmpty(sign)) {
                return generateErrorResponse(TokenEnum.SIGN_IS_EMPTY);
            }
            //appid为用户身份(可根据实际情况确定如订单号判断数据库中是否有)
            String appid = requset.getParameter("appid");
            //一、根据appid判断是否存在
            String exists = appid;
            if (StringUtils.isEmpty(exists)) {
                return generateErrorResponse(TokenEnum.APP_ID_IS_NOT_EXIST);
            }
            long timestamp = Long.valueOf(requset.getParameter("timestamp"));
            //判断是否过期
            if (isExpired(timestamp)) {
                return generateErrorResponse(TokenEnum.TOKEN_IS_EXPIRED);
            }
            // 1.鉴权
            String signUrl = getSignUrl(requset, "sign");
            String signUrlEncode = AuthenticationUtil.signUrlEncode(signUrl, appid);
            // 签名无
            if (!sign.equals(signUrlEncode)) {
                return generateErrorResponse(TokenEnum.SIGN_IS_ERROR);
            }
			/* 业务逻辑处理部分 */
        } catch (Exception e) {
            logger.error("对外接口报错:",e);
            return generateErrorResponse(TokenEnum.SERVER_ERROR);
        }
        return null;
    }

    /**
     * 服务端 获取 客户端请求 组装验证签名
     * @param req
     * @param delParams 移除不相关 的签名参数
     * @return
     */
    private String getSignUrl(HttpServletRequest req, String... delParams) {
        // 获取相对的访问路径
        String url = req.getRequestURI();
        Map<String, String> paramMap = packageRequestGetParams(req);
        if (paramMap.size() > 0) {
            // 删除
            for (int i = 0, len = delParams.length; i < len; i++) {
                paramMap.remove(delParams[i]);
            }
            return localSignUrl(url, paramMap);
        }
        return null;
    }

    /**
     * 组装签名路径
     * @param url
     * @param params
     * @return
     */
    public static String localSignUrl(String url, Map<String, String> params) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(url).append("?");
        boolean seeOne = false;
        for (String key : params.keySet()) {
            if (params.get(key) != null) {
                String lowerKey = key.toLowerCase();
                String encodeKey = lowerKey;
                String encodedValue = params.get(key);
                if (!seeOne) {
                    seeOne = true;
                } else {
                    strBuilder.append("&");
                }
                strBuilder.append(encodeKey).append("=").append(encodedValue);
            }
        }
        return strBuilder.toString();
    }

    /**
     * 解析get参数返回treemap
     * @param req
     * @return
     */
    public Map<String, String> packageRequestGetParams(
            HttpServletRequest req) {
        Map<String, String> paramMap = new TreeMap<String, String>();
        Enumeration pNames = req.getParameterNames();
        while (pNames.hasMoreElements()) {
            String key = (String) pNames.nextElement();
            String value = req.getParameter(key);
            paramMap.put(key, value);
        }
        TreeMap<String,String> paramTreeMap = new TreeMap<>(paramMap);
        return paramTreeMap;
    }

    private boolean isExpired(long timestamp){
        Long s = (System.currentTimeMillis() - timestamp)/(1000*60);
        if(s>=0 && s<=5){
            return false;
        }
        return true;
    }

    private Response generateErrorResponse(TokenEnum tokenEnum){
        Response response = new Response();
        response.setCode(tokenEnum.getCode());
        response.setDesc(tokenEnum.getDesc());
        return response;
    }
}
