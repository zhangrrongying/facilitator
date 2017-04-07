package com.ec.facilitator.base.util;

/**
 * 授权码存储接口
 * @author 张荣英
 * @date 2017年4月7日 下午10:39:28
 */
public interface IAuthTokenStorable {
	
	/**
	 * 从指定存储容器里面获取AuthToken字符串
	 * @param memberId
	 * @return
	 * @throws Exception
	 * @return String
	 * @author 张荣英
	 * @date 2017年4月7日 下午10:39:14
	 */
	public String getAuthToken(String memberId) throws Exception;
	
	/**
	 * 设置授权数据到指定的存储容器里面
	 * @param memberId
	 * @param authData
	 * @return
	 * @throws Exception
	 * @return String
	 * @author 张荣英
	 * @date 2017年4月7日 下午10:38:56
	 */
	public String setAuthToken(String memberId, AuthData authData) throws Exception;
}
