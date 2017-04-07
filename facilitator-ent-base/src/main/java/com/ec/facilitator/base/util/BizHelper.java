package com.ec.facilitator.base.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

/**
 * 业务帮助类
 * @author 张荣英
 * @date 2017年4月7日 下午10:36:29
 */
public class BizHelper {
	public static final String ENCODING_MD5 = "MD5";
	
	/**
	 * 指定加密
	 * @param mode
	 * @param str
	 * @return
	 * @throws Exception
	 * @return String
	 * @author 张荣英
	 * @date 2017年4月7日 下午10:13:04
	 */
	 public static String encodeStr(String mode, String str) throws Exception {
		 MessageDigest messageDigest = MessageDigest.getInstance(mode);
		 messageDigest.update(str.getBytes());
		 return new String(Base64.encodeBase64(messageDigest.digest()),StandardCharsets.UTF_8);
     }
}
