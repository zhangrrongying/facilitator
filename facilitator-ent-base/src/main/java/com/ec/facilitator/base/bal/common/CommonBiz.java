package com.ec.facilitator.base.bal.common;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ec.facilitator.base.dal.system.SysUserDao;
import com.ec.facilitator.base.model.system.SysOrgModel;
import com.ec.facilitator.base.util.JSONHelper;

/**
 * 组织结构
 * @author 张荣英
 * @date 2017年4月5日 下午5:22:14
 */
@Component
public class CommonBiz {
	
	@Autowired
	SysUserDao sysUserDao;
	
	
	/**
	 * 查询部门code
	 * @param userId
	 * @return
	 * @throws Exception
	 * @return String
	 * @author 张荣英
	 * @date 2017年4月5日 下午5:22:41
	 */
	public String getOrgCodeByUserId(int userId) throws Exception{
		List<SysOrgModel> orgList = sysUserDao.getOrgsByUserId(userId);
		List<String> codes = new ArrayList<String>();
		if(orgList != null && orgList.size() > 0){
			for(SysOrgModel org : orgList){
				codes.add(org.getCode());
			}
		}
	    return JSONHelper.writeValueAsString(codes).replace("[", "").replace("]", "");
	}
}
