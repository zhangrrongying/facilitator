package com.ec.facilitator.base.util;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 用于对认证授权数据的操作管理类
 * @author 张荣英
 * @date 2017年4月7日 下午10:24:08
 */
@Component
public class AuthManager {

	public final static String AUTH_TOKEN_KEY = "auth_token_key";
	public final static String REDIRECT_KEY = "redirect_key";
	public final static String AUTH_TOKEN_LOGOUT = "auth_token_logout";
	public final static String AUTH_TOKEN_SPLITER = "\n";
	public final static String PERMISSION_SPLITER = ",";
	public final static String AUTH_TOKEN_MIX_CHAR = "sunyuki$$123";

	private static AuthData s_authDataForTest = null;


	/**
	 * 获取当前登录用户的授权数据
	 * @return
	 * @return AuthData
	 * @author 张荣英
	 * @date 2017年4月7日 下午10:26:02
	 */
	public static AuthData getCurrentAuthData() {
		if (s_authDataForTest != null) {
			return s_authDataForTest;
		}
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest();
			return (AuthData) request.getAttribute(AuthData.AUTH_DATA_KEY);
		} catch (Exception ex) {
			return null;
		}
	}
	
	/**
	 * 检查是否有传入的Func的权限
	 */
	public static boolean hasFunc(String funcKey) {
		AuthData authData = getCurrentAuthData();
		if (authData != null 
			&& authData.getPermissionCode() != null 
			&& authData.getPermissionCode().size() > 0
			&& authData.getPermissionCode().contains(funcKey)) {
			return true;
		}
		return false;
	}

	/**
	 * 登录后设置AuthToken，用于回写到auth cookie到客户端
	 * @param authData
	 * @param redirect
	 * @return void
	 * @author 张荣英
	 * @date 2017年4月7日 下午10:29:16
	 */
	public static void setAuthToken(AuthData authData, Boolean redirect) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		request.setAttribute(AUTH_TOKEN_KEY, authData);
		request.setAttribute(REDIRECT_KEY, redirect);
	}

	/**
	 * 登录后设置AuthToken，用于回写到auth cookie到客户端
	 * @param authData
	 * @return void
	 * @author 张荣英
	 * @date 2017年4月7日 下午10:29:52
	 */
	public static void setAuthToken(AuthData authData) {
		setAuthToken(authData, true);
	}

	/**
	 * 安全登出系统 void
	 * @return void
	 * @author 张荣英
	 * @date 2017年4月7日 下午10:30:10
	 */
	public static void logout() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		request.setAttribute(AUTH_TOKEN_LOGOUT, true);
	}

	/**
	 * 获取用于https下面的authToken
	 * @param authData
	 * @return
	 * @throws Exception
	 * @return String
	 * @author 张荣英
	 * @date 2017年4月7日 下午10:30:30
	 */
	public static String getAuthTokenSSL(AuthData authData) throws Exception {
		if (authData != null) {
			return encryptTokenSSL(authData);
		}
		return null;
	}

	/**
	 * 获取用于http下面的authToken
	 * @param authData
	 * @return
	 * @throws Exception
	 * @return String
	 * @author 张荣英
	 * @date 2017年4月7日 下午10:31:17
	 */
	public static String getAuthToken(AuthData authData) throws Exception {
		if (authData != null) {
			return encryptToken(authData);
		}
		return null;
	}

	/**
	 * 用于SSL的authToken加密
	 * @param authData
	 * @return
	 * @throws Exception
	 * @return String
	 * @author 张荣英
	 * @date 2017年4月7日 下午10:31:30
	 */
	@SuppressWarnings("deprecation")
	public static String encryptTokenSSL(AuthData authData) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(authData.getId()).append(AUTH_TOKEN_SPLITER);
		sb.append(authData.getUserName()).append(AUTH_TOKEN_SPLITER);
		if (authData.getUpdateTokenTime() != null) {
			sb.append(DatetimeUtil.parseDateToStr(authData.getUpdateTokenTime(), DatetimeUtil.DATE_PATTERN_1)).append(AUTH_TOKEN_SPLITER);
		}else{
			sb.append(new Date(2015,10,10)).append(AUTH_TOKEN_SPLITER);
		}
		if (authData.getPermissionCode() != null) {
			for (String permission : authData.getPermissionCode()) {
				sb.append(permission).append(PERMISSION_SPLITER);
			}
		}
		return AESHelper.encrypt(sb.toString() + AUTH_TOKEN_MIX_CHAR);
	}

	/**
	 * 用于非SSL的authToken加密
	 * @param authData
	 * @return
	 * @throws Exception
	 * @return String
	 * @author 张荣英
	 * @date 2017年4月7日 下午10:31:46
	 */
	public static String encryptToken(AuthData authData) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(authData.getId()).append(AUTH_TOKEN_SPLITER);
		sb.append(authData.getUserName()).append(AUTH_TOKEN_SPLITER);
		if (authData.getUpdateTokenTime() != null) {
			sb.append(authData.getUpdateTokenTime()).append(AUTH_TOKEN_SPLITER);
		}else{
			sb.append(new Date()).append(AUTH_TOKEN_SPLITER);
		}
		if (authData.getPermissionCode() != null) {
			for (String permission : authData.getPermissionCode()) {
				sb.append(permission).append(PERMISSION_SPLITER);
			}
		}
		return AESHelper.encrypt(sb.toString());
	}

	/**
	 * 解密authToken，并返回AuthData;
	 * @param ssl
	 * @param token
	 * @return
	 * @return AuthData
	 * @author 张荣英
	 * @date 2017年4月7日 下午10:32:01
	 */
	public static AuthData decryptToken(boolean ssl, String token) {
		if (token == null) {
			return null;
		}
		String clearToken = null;
		try {
			clearToken = AESHelper.decrypt(token);
		} catch (Exception e) {
			return null;
		}
		if (ssl) {
			try {
				clearToken = clearToken.substring(0, clearToken.length() - AuthManager.AUTH_TOKEN_MIX_CHAR.length());
			} catch (Exception e) {
				return null;
			}
		}
		String[] authDataArr = clearToken.split(AuthManager.AUTH_TOKEN_SPLITER);
		if (authDataArr.length < 3 || authDataArr.length > 4) {
			return null;
		}

		AuthData data = new AuthData();
		try {
			data.setId(Integer.parseInt(authDataArr[0]));
		} catch (Exception e) {
			return null;
		}
		data.setUserName(authDataArr[1]);
		Date date = null;
		date = DatetimeUtil.parseStrToDate(authDataArr[2], DatetimeUtil.DATE_PATTERN_1);
		data.setUpdateTokenTime(date);
		// 只转换ssl的authtoken的授权数据
		if (ssl) {
			// 转换授权数据
			if (authDataArr.length == 4) {
				String permissionCode = authDataArr[3];
				if (permissionCode != null && permissionCode.trim().length() != 0) {
					data.setPermissionCode(Arrays.asList(permissionCode.split(AuthManager.PERMISSION_SPLITER)));
				}
			}
		}
		return data;
	}
	
	
	/**
	 * 解密authToken，并返回AuthData;
	 * @param ssl
	 * @param token
	 * @return
	 * @return AuthData
	 * @author 张荣英
	 * @date 2017年4月7日 下午10:32:22
	 */
	public static AuthData decryptTokenOld(boolean ssl, String token) {
		if (token == null) {
			return null;
		}
		String clearToken = null;
		try {
			clearToken = AESHelper.decrypt(token);
		} catch (Exception e) {
			return null;
		}
		// userId|userName|permissionCode+mix_char
		if (ssl) {
			try {
				clearToken = clearToken.substring(0, clearToken.length()
						- AuthManager.AUTH_TOKEN_MIX_CHAR.length());
			} catch (Exception e) {
				return null;
			}
		}
		String[] authDataArr = clearToken.split(AuthManager.AUTH_TOKEN_SPLITER);
		if (authDataArr.length < 2 || authDataArr.length > 3) {
			return null;
		}

		AuthData data = new AuthData();
		try {
			data.setId(Integer.parseInt(authDataArr[0]));
		} catch (Exception e) {
			return null;
		}
		data.setUserName(authDataArr[1]);
		// 只转换ssl的authtoken的授权数据
		if (ssl) {
			// 转换授权数据
			if (authDataArr.length == 3) {
				String permissionCode = authDataArr[2];
				if (permissionCode != null
						&& permissionCode.trim().length() != 0) {
					data.setPermissionCode(Arrays.asList(permissionCode
							.split(AuthManager.PERMISSION_SPLITER)));
				}
			}
		}
		return data;
	}
}
