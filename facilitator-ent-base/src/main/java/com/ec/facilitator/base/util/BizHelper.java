package com.ec.facilitator.base.util;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;


/**
 * 业务帮助类
 * @author ryan
 *
 */
public class BizHelper {
	// 读取远程数据超时时间，单位ms
	public static final int REMOTE_READ_TIMEOUT = 10000;
	
	public static final String ENCODING_MD5 = "MD5";
	
	/**
	 * HexMD5加密
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * @return String
	 * @author ryan 
	 * @date 2016年6月22日 上午10:52:03
	 */
	public static String encodeToHexMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest messageDigest = null;
		messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.reset();
		messageDigest.update(str.getBytes("UTF-8"));
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			} else {
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
		}
		return md5StrBuff.toString();
	}
	
	/**
	 * 指定加密
	 * @param str 待加密字符串
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * @return String
	 * @author liuchun 
	 * @date 2015年11月3日 下午4:48:53
	 */
	 public static String encodeStr(String mode, String str) throws Exception {
		 MessageDigest messageDigest = MessageDigest.getInstance(mode);
		 messageDigest.update(str.getBytes());
		 return new String(Base64.encodeBase64(messageDigest.digest()),StandardCharsets.UTF_8);
     }
	 
	 /**
	  * 获取散裂值后的int返回值
	  * @param str
	  * @param limit
	  * @return
	  * @throws NoSuchAlgorithmException
	  * @return String
	  * @author ryan 
	  * @date 2015年12月2日 下午3:45:12
	  */
	 public static String hashStringToInt(String str, int limit) throws NoSuchAlgorithmException {
		 MessageDigest md = null;
	     String outStr = null;
	     md = MessageDigest.getInstance("SHA-1");
	     byte[] digest = md.digest(str.getBytes());
	     outStr = DatatypeConverter.printHexBinary(digest);
	     StringBuilder sb = new StringBuilder();
	     for(int i = 0; i < outStr.length(); i++){
	    	 char c = outStr.charAt(i);
	    	 int cVal =Character.getNumericValue(c);
	    	 if (cVal >= 48 && cVal <= 57) {
	    		 sb.append(c);
	    	 } else {
	    		 sb.append(cVal);
	    	 }
	     }	     
	     if (limit > 0 && limit < sb.length()) {
	    	 return sb.substring(0, limit);
	     } else {
	    	 return sb.toString();
	     }
	 }
	 
	 public static String encodeSHA1(String str) {
		 String secret = DigestUtils.shaHex(str);
		 return secret;
	 }
	 
	 public static String generateSerial(String prefix, Long id) {
		 String serial = "";
		 String idStr = String.valueOf(id);
		 String time = TimeUtil.Date2String(new Date(), false).substring(2);
		 time = time.replaceAll("-", "");
		 int len = idStr.length();
		 if (len < 5) {
			 idStr = StringUtils.leftPad(idStr, 5, '0');
		 } else if (len > 5) {
			 idStr = idStr.substring(0, 5); 
		 }
		 serial+=prefix + time + idStr;
		 return serial;
	 }
	 
		/**
		 * 序列化为XML
		 * @param entity
		 * @return
		 * @return String
		 * @author 张荣英
		 * @date 2016年3月9日 下午3:12:53
		 */
		public static <T> String formatXML(T entity) {  
	        ByteArrayOutputStream out = new ByteArrayOutputStream();  
	        XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(out));  
	        encoder.writeObject(entity);  
	        encoder.close();  
	        return out.toString();  
	    }
		
		public static boolean isContainsCompany(String comps, String selectedComp) {
			String[] companys = comps.split(",");
			List<String> list = Arrays.asList(companys);
			if ((StringUtils.isNotBlank(selectedComp)) && list.contains(selectedComp)) {
				return true;
			}
			
			return false;
			
		}
}
