package com.ec.facilitator.base.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

/**
 * 常用的字符串处理类
 * @author 张荣英
 * @date 2017年4月7日 下午10:46:36
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
	 * @author 张荣英
	 * @date 2017年4月7日 下午10:46:51
	 */
	public static String encodeStrWithBase64(String mode, String str) throws Exception {
		MessageDigest messageDigest = MessageDigest.getInstance(mode);
		messageDigest.update(str.getBytes());
		return new String(Base64.encodeBase64(messageDigest.digest()),StandardCharsets.UTF_8);
	}
}
