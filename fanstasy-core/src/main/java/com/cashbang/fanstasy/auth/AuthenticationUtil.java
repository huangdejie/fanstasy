package com.cashbang.fanstasy.auth;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.StringUtils;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 
 * 接口鉴权工具类
 * 
 * @author gwx 2017-12-23
 *
 */
public class AuthenticationUtil {
	private static final String MAC_NAME = "HmacSHA1";
	private static final String ENCODING = "UTF-8";

	/**
	 * 加密签名路径生成签名
	 * 必要参数
	 * 		appid：服务端定义
	 * 		timestamp：时间戳
	 * 		sign:签名  signUrlEncode(String signUrl, String encryptKey)
	 * sighUrl生成方式:
	 * 		请求路径(除去ip或域名)+"?"+参数(参数除去sign外按参数名称全部小写并排序)
	 * 		如请求路径为http://localhost:8082/fanstasy/user/logins?loginName=hello&password=sssss&sign=28u1diegKTtLIy/CgzuKnAT0mNs=&appid=hello&timestamp=1587114647348
	 * 		则signurl为/fanstasy/user/logins?appid=hello&loginname=hello&password=sssss&timestamp=1587114647348
	 * 
	 * @param signUrl
	 * @param appid
	 * @return
	 * @throws Exception
	 */
	public static String signUrlEncode(String signUrl, String appid) throws Exception {
		byte[] signByte = hmacSHA1Encrypt(signUrl, appid);
		String localSign = Base64.encodeBase64String(signByte);
		return localSign;
	}

	/**
	 * 使用 HMAC-SHA1 签名方法对对encryptText进行签名
	 *
	 * @param encryptText
	 *            被签名的字符串
	 * @param encryptKey
	 *            密钥
	 * @return
	 * @throws Exception
	 */
	private static byte[] hmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception {
		byte[] data = encryptKey.getBytes(ENCODING);
		// 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
		Mac mac = Mac.getInstance(MAC_NAME);
		SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
		// 生成一个指定 Mac 算法 的 Mac 对象
		// 用给定密钥初始化 Mac 对象
		mac.init(secretKey);

		byte[] text = encryptText.getBytes(ENCODING);
		// 完成 Mac 操作
		return mac.doFinal(text);
	}







}
