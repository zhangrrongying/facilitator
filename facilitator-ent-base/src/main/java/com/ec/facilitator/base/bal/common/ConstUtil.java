package com.ec.facilitator.base.bal.common;

/**
 * 静态数据类
 * @author ryan
 * @date 2015年6月2日 下午3:39:44
 */
public class ConstUtil {
	
	public final static String SYS_ERP_CODE = "ERP";
	public final static String SYS_POS_CODE = "POS";
	public final static String SYS_SXE_CODE = "SXE";
	
	//QUEUE NAME
	public final static String QUEUE_NAME_ERP_TO_POS_ACCT = "q.sunyuki.erp.pos.acct";
	public final static String QUEUE_NAME_ERP_TO_WEB_ACCT = "q.sunyuki.erp.web.acct";
	public final static String QUEUE_NAME_ERP_TO_WEB_PAYMENT = "q.sunyuki.erp.web.payment";
	public final static String QUEUE_NAME_ERP_TO_POS_CONTENT = "q.sunyuki.erp.pos.content";
	public final static String QUEUE_NAME_ERP_TO_WEB_CONTENT = "q.sunyuki.erp.web.content";
	public final static String QUEUE_NAME_ERP_TO_POS_PAYMENT = "q.sunyuki.erp.pos.payment";
	public final static String QUEUE_NAME_ERP_TO_U8 = "q.sunyuki.erp.erp.u8";

	
	
	//EXCHANGE NAME
	public final static String EXCHANGE_NAME_ERP_TO_ALL_PAYMENT = "e.fanout.sunyuki.erp.payment";
	public final static String EXCHANGE_NAME_ERP_TO_ALL_ACCT = "e.fanout.sunyuki.erp.acct";
	public final static String EXCHANGE_NAME_ERP_TO_ALL_CONTENT = "e.fanout.sunyuki.erp.content";
	
	//ACTION NAME
	public final static String ACTION_NAME_ORDER_CREATE = "webOrderCreateAction";
	public final static String ACTION_NAME_MEMBER_CREATE = "webMemberCreateAction";
	public final static String ACTION_NAME_ADDRESS_CREATE = "webShippingAddressCreateAction";
	public final static String ACTION_NAME_NEW_CARD_CREATE = "webNewCardCreateAction";
	public final static String ACTION_NAME_NEW_CARD_UPDATE = "webNewCardUpdateAction";
	public final static String ACTION_NAME_OLD_CARD_UPDATE = "webOldCardCreateAction";
	public final static String ACTION_NAME_CRM_MEMBER_CREATE = "CrmClientAction";
	
	//新系统MQ action
	public static final String ACTION_NAME_CBSTOCK_UPDATE = "erpCBStockUpdateAction";
	public static final String ACTION_NAME_CARD_ORDER_PAY_NOTIFY = "erpCardOrderPayNotifyAction";
	public static final String ACTION_NAME_NORMAL_ORDER_PAY_NOTIFY = "erpNormalOrderPayNotifyAction";
	public static final String ACTION_NAME_CYCLE_BUY_ORDER_PAY_NOTIFY = "erpCycleBuyOrderPayNotifyAction";
	public static final String ACTION_NAME_NORMAL_ORDER_VOID_NOTIFY = "erpNormalOrderVoidNotifyAction";
	public static final String ACTION_NAME_CYCLE_BUY_ORDER_VOID_NOTIFY = "erpCycleBuyOrderVoidNotifyAction";
	public static final String ACTION_NAME_TO_U8 = "WebToU8Action";
	public static final String ACTION_NAME_REFUND = "erpRefundAction";
	public static final String ACTION_NAME_EACH_ORDER_OPERATION_TO_DONE = "erpEachOrderOperationToDoneAction";
	public static final String ACTION_NAME_ORDER_STATUS_UPDATE = "webOrderStatusUpdateAction";
	public static final String ACTION_NAME_ORDER_UPDATE = "webOrderUpdateAction";
	public static final String ACTION_NAME_ORDER_RECEIPT_REFUND_NOTIFY = "shopOrderRefundAndReceiptAction";
	public static final String ACTION_NAME_SCORE_CLIENT = "erpCrmScoreClientAction";
	public static final String ACTION_NAME_SCORE_HISTORY = "erpCrmScoreHistoryAction";
	
	//newerp
	public static final String ACTION_NAME_SIGN_CARD_CREATE_OR_UPDATE = "webSignCardCreateOrUpdateAction";
	public static final String ACTION_NAME_ACTUAL_STOCK_CHANGE = "webUpdateQ4SAction";
	public static final String ACTION_NAME_VIRTUAL_STOCK_CHANGE = "webVirtualStockCreateAction";
	public static final String ACTION_NAME_COUPON_STATUS_CHANGE = "erpCouponStatusAction";
	public static final String ACTION_NAME_ECMS_SYS_SAVE_USER = "ecmsSysSaveUserAction";
	public static final String ACTION_NAME_ECMS_SYS_PWD_UPDATE = "ecmsSysPwdUpdateAction";
	
	//新ERP中crm模块action
	public static final String ACTION_NAME_SCORE_BASE_CREATE_OR_UPDATE = "erpScoreBaseAction";
	public static final String ACTION_NAME_SCORE_RULE_UPDATE = "erpScoreRuleAction";
}
