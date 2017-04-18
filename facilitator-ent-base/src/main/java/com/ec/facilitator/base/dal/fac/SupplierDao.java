package com.ec.facilitator.base.dal.fac;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ec.facilitator.base.model.common.JQGridResponseModel;
import com.ec.facilitator.base.model.fac.FacSupplierModel;
import com.ec.facilitator.base.util.SpringGuzzBaseDao;

/**
 * 供应商数据访问层
 * @author 张荣英
 * @date 2017年4月18日 上午11:00:22
 */
@Component
public class SupplierDao extends SpringGuzzBaseDao {
	
	@SuppressWarnings("unchecked")
	public JQGridResponseModel<FacSupplierModel> getSupplierList(String name,int page,int limit){
		JQGridResponseModel<FacSupplierModel> result = new JQGridResponseModel<FacSupplierModel>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		if(page == 0){
			page = page + 1;
		}
		params.put("offset", (page-1)*limit);
		params.put("limit", limit);
		params.put("name", name);
		int totalSize = (int)this.findCell00("queryBkdSupplierSize", params, "int");
		List<FacSupplierModel> supplierList = this.list("queryBkdSuppliers",params);
		result.setRecords(totalSize);
		result.setRows(supplierList);
		result.setPage(page);
		result.setTotal(totalSize%limit==0?totalSize/limit:totalSize/limit+1);
		return result;
	} 
}
