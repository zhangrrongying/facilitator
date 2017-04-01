package com.ec.facilitator.base.bal.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.ApplicationContext;

public class BizErrorHelper {

	/**
	 * 
	 * @param springContext 
	 * @param masterErrorKey 主错误key
	 * @param tranErrorKey  详细错误key
	 * @param tags  错误关联资源
	 * @return
	 * @description TODO
	 * @return BizErrorModel
	 * @author zx
	 * @date 2015年6月4日 上午11:13:26
	 */
	public static BizErrorModel getBizErrorModel(ApplicationContext springContext, String masterErrorKey,String tranErrorKey) {
		BizErrorModel bizError = new BizErrorModel();
		String masterError = springContext.getMessage(masterErrorKey, null,
				Locale.getDefault());
		bizError.setReason(masterError);
		List<BizErrorItemModel> errors = new ArrayList<BizErrorItemModel>();
		BizErrorItemModel bizErrorItemModel = new BizErrorItemModel();
		bizErrorItemModel.setErrorCode(tranErrorKey);
		bizErrorItemModel.setReason(springContext.getMessage(masterErrorKey+ "_" + tranErrorKey,
				null, Locale.getDefault()));
		errors.add(bizErrorItemModel);
		bizError.setErrors(errors);
		return bizError;
	}
	
}
