package com.ec.facilitator.site.reset;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ec.facilitator.base.bal.fac.SupplierBiz;
import com.ec.facilitator.base.model.common.BooleanResultModel;
import com.ec.facilitator.base.model.common.JQGridResponseModel;
import com.ec.facilitator.base.model.fac.FacSupplierModel;
import com.ec.facilitator.base.model.fac.JQGridSupplierModel;
import com.ec.facilitator.base.util.AuthManager;
import com.ec.facilitator.base.util.AuthTag;
import com.ec.facilitator.base.util.SSLTag;

@RestController
@RequestMapping("/supplier")
public class SupplierResetController {	
	
		@Resource
		SupplierBiz supplierBiz;
	
		@AuthTag
		@SSLTag
		@RequestMapping(value = "/list.json", method = RequestMethod.POST)
		public @ResponseBody JQGridResponseModel<FacSupplierModel> userList(@RequestBody JQGridSupplierModel requestModel) throws Exception {
			return supplierBiz.getSupplierList(requestModel);
		}
		
		@AuthTag
		@SSLTag
		@RequestMapping(value = "/save.json", method = RequestMethod.POST)
		public BooleanResultModel saveSupplier(@RequestBody FacSupplierModel supplier) throws Exception {
			return supplierBiz.saveSupplier(AuthManager.getCurrentAuthData().getId(),supplier);
		}
		
}
