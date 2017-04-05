package com.ec.facilitator.base.bal.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.ApplicationContext;

public class BizErrorHelper {

	/**
	 * 错误关联资源
	 * @param springContext
	 * @param masterErrorKey
	 * @param tranErrorKey
	 * @return
	 * @return BizErrorModel
	 * @author 张荣英
	 * @date 2017年4月5日 下午5:20:32
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
