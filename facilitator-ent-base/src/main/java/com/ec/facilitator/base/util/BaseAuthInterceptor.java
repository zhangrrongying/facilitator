package com.ec.facilitator.base.util;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 用于拦截认证授权的类
 * @author 张荣英
 * @date 2017年4月7日 下午10:34:06
 */
public abstract class BaseAuthInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	protected WebConfig webContext;
	
	@Autowired
	protected ApplicationContext springContext;
	
	public final static String AUTH_TOKEN_SSL_HEADER = "x-%s-authtoken-ssl";
	public final static String USER_AGENT_HEADER = "user-agent";
	public final static String AUTH_TOKEN_HEADER = "x-%s-authtoken";
	
	/**
	 * 验证当前请求是否合法
	 * @param handler
	 * @param request
	 * @param token
	 * @return
	 * @return HttpStatus
	 * @author 张荣英
	 * @date 2017年4月7日 下午10:34:22
	 */
	protected HttpStatus validate(Object handler, 
							      HttpServletRequest request,
								  String token) {

		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			//判断Action方法是否标记了AuthTag,如果标记证明该方法需要认证和授权后才能访问；
			AuthTag auth = ((HandlerMethod) handler).getMethodAnnotation(AuthTag.class);
			//判断Action方法是否标记了SSLTag，如果标记了证明该Action只能在https下面访问；
			SSLTag sslTag = ((HandlerMethod) handler).getMethodAnnotation(SSLTag.class);
			if (sslTag != null) {
				String[] secureHosts = webContext.getSecureHost().split(";");
				boolean isUnauthorized = true;
				if (secureHosts.length > 0) {
					for(String secureHost : secureHosts) {
						if (request.getRequestURL().toString().toLowerCase().contains(secureHost)) {
							isUnauthorized = false;
							break;
						}
					}
				}
				if (isUnauthorized) {
					return HttpStatus.UNAUTHORIZED;
				}
			}
			if (auth != null) {
				HttpStatus status = parseAuthTokenToAuthData(request, token, true, auth.value());
				if (status == HttpStatus.OK || status == HttpStatus.NOT_ACCEPTABLE || status == HttpStatus.FORBIDDEN) {
					return status;
				} else {
					return parseAuthTokenToAuthDataOld(request, token, true, auth.value());
				}
			}
		}
		return HttpStatus.OK;
	}
	
	/**
	 * 转换Token到程序内部能够识别的AuthData
	 * @param request
	 * @param token
	 * @param ssl
	 * @param authValue
	 * @return
	 * @return HttpStatus
	 * @author 张荣英
	 * @date 2017年4月7日 下午10:34:58
	 */
	protected HttpStatus parseAuthTokenToAuthData(HttpServletRequest request, 
												  String token, 
												  boolean ssl, 
												  String authValue) {
		if (token == null) {	
			return HttpStatus.UNAUTHORIZED;
		}
		String clearToken = null;
		try {
			clearToken = AESHelper.decrypt(token);
		} catch (Exception e) {
			return HttpStatus.UNAUTHORIZED;
		}
		//userId|userName|updateTokenTime|permissionCode+mix_char
		if (ssl) {
			try {
				clearToken = clearToken.substring(0, clearToken.length() - AuthManager.AUTH_TOKEN_MIX_CHAR.length());
			} catch (Exception e) {
				return HttpStatus.UNAUTHORIZED;
			}
		}
		String[] authDataArr = clearToken.split(AuthManager.AUTH_TOKEN_SPLITER);
		if (authDataArr.length < 3 || authDataArr.length > 4) {
			return HttpStatus.UNAUTHORIZED;
		}

		AuthData data = new AuthData();
		try {
			data.setId(Integer.parseInt(authDataArr[0]));
		} catch (Exception e) {
			return HttpStatus.UNAUTHORIZED;
		}
		data.setUserName(authDataArr[1]);
		
		//只转换ssl的authtoken的授权数据
		if (ssl) {
			
			if(webContext.getTokenStorage()) {
				//让客户端authtoken和服务器端authtoken做验证
				IAuthTokenStorable  serverAuthToken = springContext.getBean("authTokenStorage", IAuthTokenStorable.class);
				try {
					if (serverAuthToken != null && token.equals(serverAuthToken.getAuthToken(String.valueOf(data.getId()))) == false) {
						return HttpStatus.NOT_ACCEPTABLE;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			//转换授权数据
			if (authDataArr.length == 4) {
				String permissionCode = authDataArr[3];
				if (permissionCode != null
						&& permissionCode.trim().length() != 0) {
					data.setPermissionCode(Arrays.asList(permissionCode
							.split(AuthManager.PERMISSION_SPLITER)));
					if (authValue.length() == 0 || authValue == null) {
						request.setAttribute(AuthData.AUTH_DATA_KEY, data);
						return HttpStatus.OK;
					}
					if (data.getPermissionCode().contains(authValue)) {
						request.setAttribute(AuthData.AUTH_DATA_KEY, data);
						return HttpStatus.OK;
					} else {
						return HttpStatus.FORBIDDEN;
					}
				}
			}
		}
		request.setAttribute(AuthData.AUTH_DATA_KEY, data);
		return HttpStatus.OK;
	}
	
	protected HttpStatus parseAuthTokenToAuthDataOld(
			HttpServletRequest request, String token, boolean ssl,
			String authValue) {
		if (token == null) {
			return HttpStatus.UNAUTHORIZED;
		}
		String clearToken = null;
		try {
			clearToken = AESHelper.decrypt(token);
		} catch (Exception e) {
			return HttpStatus.UNAUTHORIZED;
		}
		// userId|userName|permissionCode+mix_char
		if (ssl) {
			try {
				clearToken = clearToken.substring(0, clearToken.length()
						- AuthManager.AUTH_TOKEN_MIX_CHAR.length());
			} catch (Exception e) {
				return HttpStatus.UNAUTHORIZED;
			}
		}
		String[] authDataArr = clearToken.split(AuthManager.AUTH_TOKEN_SPLITER);
		if (authDataArr.length < 2 || authDataArr.length > 3) {
			return HttpStatus.UNAUTHORIZED;
		}

		AuthData data = new AuthData();
		try {
			data.setId(Integer.parseInt(authDataArr[0]));
		} catch (Exception e) {
			return HttpStatus.UNAUTHORIZED;
		}
		data.setUserName(authDataArr[1]);
		// 只转换ssl的authtoken的授权数据
		if (ssl) {
			// 转换授权数据
			if (authDataArr.length == 3) {
				if (authValue.length() == 0 || authValue == null) {
					request.setAttribute(AuthData.AUTH_DATA_KEY, data);
					return HttpStatus.OK;
				} else {
					String permissionCode = authDataArr[2];
					if (permissionCode != null
							&& permissionCode.trim().length() != 0) {
						data.setPermissionCode(Arrays.asList(permissionCode
								.split(AuthManager.PERMISSION_SPLITER)));
						if (data.getPermissionCode().contains(authValue)) {
							request.setAttribute(AuthData.AUTH_DATA_KEY, data);
							return HttpStatus.OK;
						} else {
							return HttpStatus.FORBIDDEN;
						}
					}
				}
			}
		}
		request.setAttribute(AuthData.AUTH_DATA_KEY, data);
		return HttpStatus.OK;
	}
	
	/**
	 * 处理请求过来的UserAgent
	 * @param request
	 * @return void
	 * @author 张荣英
	 * @date 2017年4月7日 下午10:35:50
	 */
	protected void processUserAgent(HttpServletRequest request) {
		String userAgent = request.getHeader(USER_AGENT_HEADER);
		request.setAttribute(WebConfig.USER_AGENT_KEY, userAgent);
	}
}
