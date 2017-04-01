package com.ec.facilitator.base.util;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;

import com.ec.facilitator.base.dal.system.SysUserDao;
import com.ec.facilitator.base.model.system.SysUserModel;
/**
 * @ClassName: AuthTokenStoreBiz
 * @Description: 实现authtoken的存储逻辑
 * @author ryan
 * @date 2015年2月9日 下午4:35:55
 */
public class AuthTokenStoreBiz implements IAuthTokenStorable {
	
	private final static String TOKEN_PREFIX = "group_user:%d:auth_token";
	
	@Autowired
	private SysUserDao userDao;
	
	@Resource(name = "springRedisTemplate")
	private ValueOperations<String, String> valOps;

	
	@Autowired
	private WebConfig webConfig;

	@Override
	public String getAuthToken(String userId) throws Exception {
		Integer userKey = Integer.valueOf(userId);
		String token = this.getAuthTokenFromRedis(userKey);
		
		if(null != token && !"".equals(token)){
			return token;
		}
		
		SysUserModel user = (SysUserModel)userDao.findObjectByPK(SysUserModel.class, userId);
		if (user == null) {
			return null;
		}
		
		AuthData authData = new AuthData();
		authData.setId(Integer.parseInt(userId));
		authData.setUserName(user.getName());
		token = AuthManager.encryptTokenSSL(authData);
		setAuthTokenInRedis(userKey, token);
		
		return token;
	}
	
	@Override
	public String setAuthToken(String userId, AuthData authData) throws Exception {
		String token = AuthManager.encryptTokenSSL(authData);
		Integer userKey = Integer.valueOf(userId);
		this.setAuthTokenInRedis(userKey, token);
		return token;
	}

	/**
	 * @Title: removeAuthTokenFromRedis
	 * @Description: 删除autotoken从redis中
	 * @param key
	 * void
	 * @author ryan
	 * @date 2015年2月9日 下午4:32:45
	 * @throws
	 */
	public void removeAuthTokenFromRedis(Integer key) {
		try {
			
			valOps.getOperations().delete(String.format(TOKEN_PREFIX, key));
		}
		catch(Throwable ex) {
			SLogger.error(ex);
		}
	}
	
	/**
	 * 设置authtoken到指定的redis中
	 */
	private void setAuthTokenInRedis(Integer key, String value) {
		try {
			valOps.set(String.format(TOKEN_PREFIX, key), value);
		}
		catch(Throwable ex) {
			SLogger.error(ex);
		}
	}

	/**
	 * 获取authtoken从指定的redis存储容器中
	 */
	private String getAuthTokenFromRedis(Integer key) {
		try {
			return valOps.get(String.format(TOKEN_PREFIX, key));
		}
		catch(Throwable ex) {
			SLogger.error(ex);
			return null;
		}
	}
}
