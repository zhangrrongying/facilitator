/**   
 * @Title: HttpClientUtil.java
 * @Package com.sunyuki.ec.group.base.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuchun  
 * @date 2015年12月25日 下午5:03:21
 * @version V1.0   
 */
package com.ec.facilitator.base.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;

/**
 * @author liuchun
 * @date 2015年12月25日 下午5:03:21
 */
public class HttpClientUtil {
	
	private static final String DEFAULT_CHARSET = "utf-8";
	private static final String URL_SUFFIX = "api/sykapi/print";
	public static final String IS_FAIL = "IsFaile";
	public static final String MESSAGE = "Message";
	
	public static Map<String, Object> requestByGet(String url, Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpClient httpClient = new HttpClient();
		Iterator<String> it = params.keySet().iterator();
		String param = "";
		while (it.hasNext()) {
			String paramName = it.next();
			Object value = params.get(paramName);
			param += paramName + "=" + value + "&";
		}
		param = param.substring(0, param.length() - 1);
		GetMethod getMethod = new GetMethod(url + URL_SUFFIX);
		getMethod.setQueryString(param);
		getMethod.getParams().setCredentialCharset(DEFAULT_CHARSET);
		try {
			int status = httpClient.executeMethod(getMethod);
			if (status == HttpStatus.SC_OK) {
				String result = getMethod.getResponseBodyAsString();
				map = parseResult(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		
		return map;
		
	}
	
	private static Map<String, Object> parseResult(String response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String formatResult = response.replaceFirst("\\{", "").replaceFirst("\\}", "").replaceAll("\"", "");
		String[] results = formatResult.split(",");
		for (String str : results) {
			if (str.contains(IS_FAIL) || str.contains(MESSAGE)) {
				String[] temp = str.split(":");
				if (StringUtils.isNotBlank(temp[1]) && !"null".equals(temp[1])) {
					map.put(temp[0], temp[1]);
				}
			}
			
		}
		
		return map;
	}
	

}
