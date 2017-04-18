package com.ec.facilitator.base.bal.fac;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.ec.facilitator.base.dal.fac.SupplierDao;
import com.ec.facilitator.base.model.common.JQGridResponseModel;
import com.ec.facilitator.base.model.fac.FacSupplierModel;
import com.ec.facilitator.base.model.fac.JQGridSupplierModel;
import com.ec.facilitator.base.util.WebConfig;

/**
 * 供应商业务逻辑层
 * @author 张荣英
 * @date 2017年4月18日 上午10:55:32
 */
@Component
public class SupplierBiz {
	
	@Autowired
	SupplierDao supplierDao;
	
	@Autowired
	private ApplicationContext springContext;
	
	@Autowired
	private WebConfig webConfig;
	
	/**
	 * 查询供应商列表
	 * @param requestModel
	 * @return
	 * @return JQGridResponseModel<FacSupplierModel>
	 * @author 张荣英
	 * @date 2017年4月18日 上午11:21:56
	 */
	public JQGridResponseModel<FacSupplierModel> getSupplierList(JQGridSupplierModel requestModel){
		return supplierDao.getSupplierList(requestModel.getName(),requestModel.getPage(), requestModel.getRows());
	}
	
	/**
	 * 根据ID查询供应商
	 * @param id
	 * @return
	 * @return FacSupplierModel
	 * @author 张荣英
	 * @date 2017年4月18日 上午11:23:40
	 */
	public FacSupplierModel getModelById(int id){
		FacSupplierModel supplier = (FacSupplierModel)supplierDao.findObjectByPK(FacSupplierModel.class, id);
		return supplier;
	}
	
}
