package com.ec.facilitator.base.util;

/**
 * @ClassName: IAuthTokenStorable
 * @Description: 授权码存储接口
 * @author ryan
 * @date 2015年1月19日 上午10:52:05
 */
public interface IAuthTokenStorable {
	
	/**
	 * @Title: getAuthToken
	 * @Description: 从指定存储容器里面获取AuthToken字符串
	 * @param memberId
	 * @return
	 * @throws Exception
	 * String
	 * @author ryan
	 * @date 2015年2月9日 下午4:30:31
	 * @throws
	 */
	public String getAuthToken(String memberId) throws Exception;
	
	/**
	 * @Title: setAuthToken
	 * @Description: 设置授权数据到指定的存储容器里面
	 * @param memberId
	 * @param authData
	 * @return
	 * @throws Exception
	 * String
	 * @author ryan
	 * @date 2015年2月9日 下午4:31:25
	 * @throws
	 */
	public String setAuthToken(String memberId, AuthData authData) throws Exception;
}
