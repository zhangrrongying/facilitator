package com.ec.facilitator.base.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
	public final static String USER_AGENT_KEY = "user_agent";
	
	@Value("${secure.host:}")
	private String secureHost;
	
	@Value("${auth.token.storage:true}")
    private Boolean tokenStorage;
	
	@Value("${auth.token.key:}")
	private String tokenKey;
	
	@Value("${auth.home:index}")
    private String home;

    @Value("${auth.login:login}")
    private String login;
    
    @Value("${auth.forbidden:forbidden}")
    private String forbidden;

    @Value("${auth.logout.redirect:login}")
    private String logoutRedirect;
    
    @Value("${auth.logout:}")
    private String logout;
    
    @Value("${auth.age:1800}")
    private int age;
    
    @Value("${auth.secure:true}")
    private Boolean secure;
    
    @Value("${auth.httpOnly:true}")
    private Boolean httpOnly;
    
    @Value("${auth.domain:}")
    private String domain;
    
	@Value("${static.host:}")
	private String staticHost;
	
    public String getHome() {
		return home;
	}

	public String getLogin() {
		return login;
	}
	
	public String getLogoutRedirect() {
		return logoutRedirect;
	}
	
	public String getLogout() {
		return logout;
	}
	
	public String getForbidden() {
		return forbidden;
	}
	public int getAge() {
		return age;
	}
	
	public String getDomain() {
		return domain;
	}
	
	// 获取是否是httpOnly
	public Boolean getHttpOnly() {
		return httpOnly;
	}
	
	//SSL Auth Token是否只用于https下面
	public Boolean getSecure() {
		return secure;
	}
	
	//授权token的名称，一般用项目域名标示
	public String getTokenKey() {
		return tokenKey;
	}
	
	/**
	 * 启用token存储
	 * @return 是否启用存储
	 */
	public Boolean getTokenStorage() {
		return tokenStorage;
	}
	
	public String getSecureHost() {
		return secureHost;
	}
}