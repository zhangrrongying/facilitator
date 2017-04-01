package com.ec.facilitator.base.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

/**
 * 常用的字符串处理类
 * @author ryan
 * @date 2016年6月14日 上午11:28:02
 */
public class StrUtil {
	
	public static final String CHARSET = "UTF-8";
	
	public static final String ENCODING_MODE_MD5 = "MD5";
	
	/**
	 * 使用Base64来压缩加密后的String
	 * @param mode
	 * @param str
	 * @return
	 * @throws Exception
	 * @return String
	 * @author ryan 
	 * @date 2016年6月14日 上午11:28:44
	 */
	public static String encodeStrWithBase64(String mode, String str) throws Exception {
		MessageDigest messageDigest = MessageDigest.getInstance(mode);
		messageDigest.update(str.getBytes());
		return new String(Base64.encodeBase64(messageDigest.digest()),StandardCharsets.UTF_8);
	}
}
