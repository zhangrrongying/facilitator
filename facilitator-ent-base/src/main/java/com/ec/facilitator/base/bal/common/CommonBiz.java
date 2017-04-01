package com.ec.facilitator.base.bal.common;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ec.facilitator.base.dal.system.SysUserDao;
import com.ec.facilitator.base.model.system.SysOrgModel;
import com.ec.facilitator.base.util.JSONHelper;

/**
 * 菜品报告业务类
 * @author 张荣英
 * @date 2016年7月1日 下午3:05:58
 */
@Component
public class CommonBiz {
	
	@Autowired
	SysUserDao sysUserDao;
	
	
	/**
	 * 查询部门code
	 * @param userId
	 * @param requestModel
	 * @return
	 * @return JQGridResponseModel<ProductReportModel>
	 * @author 张荣英
	 * @throws Exception 
	 * @date 2016年7月5日 下午3:34:35
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
