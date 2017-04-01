package com.ec.facilitator.base.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.CookieGenerator;

public final class SiteAuthInterceptor extends BaseAuthInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		this.processUserAgent(request);
		
		Cookie[] cookies = request.getCookies();
		String tokenSSL = null;
		String token = null;
		if (null != cookies) {
			for (Cookie c : cookies) {
				if (c.getName().equalsIgnoreCase(String.format(AUTH_TOKEN_SSL_HEADER, webContext.getTokenKey()))) {
					tokenSSL = c.getValue();
					if (tokenSSL != null && token != null) {
						break;
					}
				} else if (c.getName().equalsIgnoreCase(String.format(AUTH_TOKEN_HEADER, webContext.getTokenKey()))) {
					token = c.getValue();
					if (tokenSSL != null && token != null) {
						break;
					}
				}
			}
		}

		HttpStatus statusCode = this.validate(handler, request, tokenSSL);

		if (statusCode == HttpStatus.UNAUTHORIZED) {
			if (request.getQueryString() != null) {
				if (request.getRequestURI().toLowerCase().contains(webContext.getLogin())) {
					response.sendRedirect(webContext.getSecureHost() + webContext.getLogin());
				} else {
					response.sendRedirect(webContext.getSecureHost() + webContext.getLogin() + "?redirectUrl="
							+ request.getRequestURI() + "?" + request.getQueryString());
				}
			} else {
				if (request.getRequestURI().toLowerCase().contains(webContext.getLogin())) {
					response.sendRedirect(webContext.getSecureHost() + webContext.getLogin());
				}
				else {
					response.sendRedirect(webContext.getSecureHost() + webContext.getLogin() + "?redirectUrl=" + request.getRequestURI()); 
				}
			}
			return false;
		} else if (statusCode == HttpStatus.FORBIDDEN) {
			response.sendRedirect(webContext.getForbidden());
			return false;
		} else if (statusCode == HttpStatus.NOT_ACCEPTABLE) {//用于处理authtoken过期的问题
			response.sendRedirect(webContext.getLogout());
			return false;
		}
		
		//用于非SSL获取会员的登录数据
		AuthData authData = AuthManager.getCurrentAuthData();
		if (authData == null) {
			this.parseAuthTokenToAuthData(request, token, false, null);
			if (AuthManager.getCurrentAuthData() == null) {
				this.parseAuthTokenToAuthDataOld(request, token, false, null);
			}
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView == null) {
			modelAndView = new ModelAndView();
		}
		
		//设置每个页面会用到的应用程序上下文
		modelAndView.addObject("webContext", webContext);
		
		//回写reponse的授权cookie
		if (request.getAttribute(AuthManager.AUTH_TOKEN_KEY) != null) {
			AuthData authData = (AuthData)request.getAttribute(AuthManager.AUTH_TOKEN_KEY);
			addAuthCookies(response, authData);			
			if ((Boolean)request.getAttribute(AuthManager.REDIRECT_KEY) == true) {
				String redirectUrl = request.getParameter("redirectUrl");
				if (redirectUrl == null || redirectUrl.length() == 0) {
					response.sendRedirect(webContext.getHome());
				} else {
					response.sendRedirect(redirectUrl);
				}
			}
		} else if (request.getAttribute(AuthManager.AUTH_TOKEN_LOGOUT) != null) {//注销登出
			removeAuthCookies(response);
		}
	}
	
	//移除掉授权authToken
	private void removeAuthCookies(HttpServletResponse response) throws Exception {
		//用于https下面的cookie
		CookieGenerator sslTokenCookie = new CookieGenerator();
		sslTokenCookie.setCookieName(String.format(AUTH_TOKEN_SSL_HEADER, webContext.getTokenKey()));
		sslTokenCookie.setCookiePath("/");
		sslTokenCookie.setCookieDomain(webContext.getDomain());
		sslTokenCookie.setCookieHttpOnly(webContext.getHttpOnly());
		sslTokenCookie.setCookieSecure(webContext.getSecure());
		sslTokenCookie.removeCookie(response);
		//用于http下面的cookie
		CookieGenerator tokenCookie = new CookieGenerator();
		tokenCookie.setCookieName(String.format(AUTH_TOKEN_HEADER, webContext.getTokenKey()));
		tokenCookie.setCookiePath("/");
		tokenCookie.setCookieDomain(webContext.getDomain());
		tokenCookie.setCookieHttpOnly(false);
		tokenCookie.setCookieSecure(false);
		tokenCookie.removeCookie(response);
		response.sendRedirect(webContext.getLogoutRedirect());
	}

	//添加授权authToken
	private void addAuthCookies(HttpServletResponse response, AuthData authData)
			throws Exception {
		CookieGenerator sslTokenCookie = new CookieGenerator();
		sslTokenCookie.setCookieName(String.format(AUTH_TOKEN_SSL_HEADER, webContext.getTokenKey()));
		if (authData.isKeepLogin()) {
			sslTokenCookie.setCookieMaxAge(webContext.getAge());
		}
		sslTokenCookie.setCookiePath("/");
		sslTokenCookie.setCookieDomain(webContext.getDomain());
		sslTokenCookie.setCookieHttpOnly(webContext.getHttpOnly());
		sslTokenCookie.setCookieSecure(webContext.getSecure());
		sslTokenCookie.addCookie(response, AuthManager.encryptTokenSSL(authData));
		
		CookieGenerator tokenCookie = new CookieGenerator();
		tokenCookie.setCookieName(String.format(AUTH_TOKEN_HEADER, webContext.getTokenKey()));
		if (authData.isKeepLogin()) {
			tokenCookie.setCookieMaxAge(webContext.getAge());
		}
		tokenCookie.setCookiePath("/");
		tokenCookie.setCookieDomain(webContext.getDomain());
		tokenCookie.setCookieHttpOnly(false);
		tokenCookie.setCookieSecure(false);
		tokenCookie.addCookie(response, AuthManager.encryptToken(authData));
	}

}
